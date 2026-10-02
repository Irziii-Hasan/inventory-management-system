package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.ShopResponseDTO;
import com.jhgadgets.ims.dto.ShopUpdateRequest;
import com.jhgadgets.ims.model.Shop;

@Mapper (componentModel = "spring")
public interface ShopMapper {
	@Mapping(source="mall.name", target = "mallName")
	ShopResponseDTO toDto(Shop shop);
	
	List<ShopResponseDTO> toDtoList(List<Shop> shop);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "mall", ignore = true)
    void updateShopFromRequest(ShopUpdateRequest source, @MappingTarget Shop target);
}
