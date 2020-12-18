package grpc.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import grpc.shop.dto.ShopDto;

@Component
@Mapper
public interface ShopDao {

	public List<ShopDto> getShopById(List<Long>  id);
	
}
