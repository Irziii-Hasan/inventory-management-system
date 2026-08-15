package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.ShopResponseDTO;
import com.jhgadgets.ims.model.Shop;

@Mapper (componentModel = "spring")
public interface ShopMapper {
	@Mapping(source="mall.name", target = "mallName")
	ShopResponseDTO toDto(Shop shop);
	
	List<ShopResponseDTO> toDtoList(List<Shop> shop);
}
