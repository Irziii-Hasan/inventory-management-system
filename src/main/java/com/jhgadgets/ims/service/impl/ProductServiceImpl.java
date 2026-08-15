package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.ProductResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.ProductMapper;
import com.jhgadgets.ims.model.Category;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.repository.CategoryRepository;
import com.jhgadgets.ims.repository.ProductRepository;
import com.jhgadgets.ims.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final CategoryRepository categoryRepository;
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
		super();
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ProductResponseDTO createProduct(Product product) {
		Category category = categoryRepository.findById(product.getCategory().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", product.getCategory().getId()));
		product.setCategory(category);
		Product savedProduct = productRepository.save(product);
		logger.info("Product created with id: {}",savedProduct.getId());
		return productMapper.toDto(savedProduct);
	}

	@Override
	public ProductResponseDTO getProductById(Long productId) {
		return productMapper.toDto(productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId)));
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		return productMapper.toDtoList(productRepository.findAll());
	}

	@Override
	public List<ProductResponseDTO> getAllProductsByCategoryId(Long categoryId) {
		return productMapper.toDtoList(productRepository.findByCategoryId(categoryId));
	}

	@Override
	public void deleteProductById(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));
		productRepository.deleteById(product.getId());
	}

}
