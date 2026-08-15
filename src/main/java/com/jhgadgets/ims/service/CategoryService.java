package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.CategoryResponseDTO;
import com.jhgadgets.ims.model.Category;

public interface CategoryService {
	CategoryResponseDTO createCategory(Category category);
	List<CategoryResponseDTO> getAllCategories();
	CategoryResponseDTO getCategoryById(Long categoryId);
	void deleteCategoryById(Long categoryId);
	
}
