package com.jhgadgets.ims.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.jhgadgets.ims.dto.CategoryResponseDTO;
import com.jhgadgets.ims.model.Category;

@Mapper (componentModel = "spring")
public interface CategoryMapper {
	
	CategoryResponseDTO toDto(Category category);
	List<CategoryResponseDTO> toDtoList(List<Category> categories);
}
