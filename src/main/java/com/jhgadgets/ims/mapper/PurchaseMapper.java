package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.PurchaseResponseDTO;
import com.jhgadgets.ims.dto.PurchaseUpdateRequest;
import com.jhgadgets.ims.model.Purchase;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
	@Mapping(source = "product.name", target = "productName")
	@Mapping(source = "shop.shopNumber", target = "shopNumber")
	@Mapping(source = "shop.mall.name", target = "mallName")
	
	PurchaseResponseDTO toDto(Purchase purchase);
	List<PurchaseResponseDTO> toDtoList(List<Purchase> purchases);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "product", ignore = true)
	@Mapping(target = "shop", ignore = true)
	@Mapping(target = "quantity", ignore = true)
	@Mapping(target = "purchasePrice", ignore = true)
	void updatePurchaseFromRequestMapping(PurchaseUpdateRequest source, @MappingTarget Purchase purchase);

}
