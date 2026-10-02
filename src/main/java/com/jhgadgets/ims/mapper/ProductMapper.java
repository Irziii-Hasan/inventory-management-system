package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jhgadgets.ims.dto.ProductResponseDTO;
import com.jhgadgets.ims.dto.ProductUpdateRequest;
import com.jhgadgets.ims.model.Product;

@Mapper (componentModel = "spring")
public interface ProductMapper {

	@Mapping(source = "category.name", target = "categoryName")

	ProductResponseDTO toDto(Product product);
	List<ProductResponseDTO> toDtoList(List<Product> products);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "category", ignore = true)
	void updateProductFromRequest(ProductUpdateRequest source, @MappingTarget Product target);
	
}
