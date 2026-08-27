package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.InventoryResponseDTO;
import com.jhgadgets.ims.model.Inventory;

@Mapper (componentModel = "spring")
public interface InventoryMapper {
	@Mapping(source = "product.name", target = "productName")
	@Mapping(source = "product.photoPath", target = "photoPath")
	
	public InventoryResponseDTO toDto(Inventory inventory);
	public List<InventoryResponseDTO> toDtoList(List<Inventory> inventories);
}
