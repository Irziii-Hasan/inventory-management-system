package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.ShopProductResponseDTO;
import com.jhgadgets.ims.model.ShopProduct;

@Mapper (componentModel = "spring")
public interface ShopProductMapper {
	@Mapping(source = "product.name", target = "productName")
	@Mapping(source = "shop.shopNumber", target = "shopNumber")
	@Mapping(source = "shop.mall.name", target = "mallName")
	
	ShopProductResponseDTO toDto(ShopProduct shopProduct);
	List<ShopProductResponseDTO> toDtoList(List<ShopProduct> shopProducts);
}
