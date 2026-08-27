package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jhgadgets.ims.dto.ProductResponseDTO;
import com.jhgadgets.ims.model.Product;

@Mapper (componentModel = "spring")
public interface ProductMapper {

	@Mapping(source = "category.name", target = "categoryName")

	ProductResponseDTO toDto(Product product);
	List<ProductResponseDTO> toDtoList(List<Product> products);
	
}
