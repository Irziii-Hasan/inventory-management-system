package com.jhgadgets.ims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhgadgets.ims.dto.CategoryResponseDTO;
import com.jhgadgets.ims.model.Category;
import com.jhgadgets.ims.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody Category category) {
		CategoryResponseDTO savedCategory = categoryService.createCategory(category);
		 return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
		List<CategoryResponseDTO> categories = categoryService.getAllCategories();
 		return new ResponseEntity<List<CategoryResponseDTO>>(categories,HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId){
		CategoryResponseDTO category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	@DeleteMapping ("/{categoryId}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId) {
		categoryService.deleteCategoryById(categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
