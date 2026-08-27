package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.PurchaseResponseDTO;
import com.jhgadgets.ims.model.Purchase;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
	@Mapping(source = "product.name", target = "productName")
	@Mapping(source = "shop.shopNumber", target = "shopNumber")
	@Mapping(source = "shop.mall.name", target = "mallName")
	
	PurchaseResponseDTO toDto(Purchase purchase);
	List<PurchaseResponseDTO> toDtoList(List<Purchase> purchases);

}
