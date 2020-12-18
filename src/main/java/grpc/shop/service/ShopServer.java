package grpc.shop.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import grpc.shop.dao.ShopDao;
import grpc.shop.dto.ShopDto;
import grpc.shop.proto.ReplyShop;
import grpc.shop.proto.ReplyShopList;
import grpc.shop.proto.RequestId;
import grpc.shop.proto.ShopGrpc.ShopImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;



@GrpcService
public class ShopServer extends ShopImplBase {
	@Autowired
	ShopDao shopDao;

	@Override
	public void  getShopById(RequestId request, StreamObserver<ReplyShopList> responseObserver) {
		// TODO Auto-generated method stub
		
		List<ShopDto> ShopList = shopDao.getShopById(request.getIdList());
		grpc.shop.proto.ReplyShopList.Builder builderList = ReplyShopList.newBuilder();
		for(ShopDto Shop : ShopList){
			grpc.shop.proto.ReplyShop.Builder builder = ReplyShop.newBuilder();
			BeanUtils.copyProperties(Shop, builder);
			ReplyShop replyShop = builder.build();
			builderList.addReplyShopList(replyShop);
		}
		ReplyShopList replyShopList = builderList.build();
		responseObserver.onNext(replyShopList);
		responseObserver.onCompleted();
	}
}
