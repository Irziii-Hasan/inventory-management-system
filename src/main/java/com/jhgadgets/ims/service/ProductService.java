package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.ProductResponseDTO;
import com.jhgadgets.ims.model.Product;

public interface ProductService {
	
	ProductResponseDTO createProduct(Product product);
	ProductResponseDTO getProductById(Long productId);
	List<ProductResponseDTO> getAllProducts();
	List<ProductResponseDTO> getAllProductsByCategoryId(Long categoryId);
	void deleteProductById(Long productId);
}
