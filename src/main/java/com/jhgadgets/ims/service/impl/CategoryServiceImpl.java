package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.CategoryResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.CategoryMapper;
import com.jhgadgets.ims.model.Category;
import com.jhgadgets.ims.repository.CategoryRepository;
import com.jhgadgets.ims.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public CategoryResponseDTO createCategory(Category category) {
		Category savedCategory = categoryRepository.save(category);
		logger.info("Category created with id :{}",savedCategory.getId());
		return categoryMapper.toDto(savedCategory);
	}

	@Override
	public List<CategoryResponseDTO> getAllCategories() {
		return categoryMapper.toDtoList(categoryRepository.findAll());
	}

	@Override
	public CategoryResponseDTO getCategoryById(Long categoryId) {
		return categoryMapper.toDto(categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)));
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		categoryRepository.deleteById(category.getId());
	}

}
