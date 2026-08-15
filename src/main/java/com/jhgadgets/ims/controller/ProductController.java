package com.jhgadgets.ims.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhgadgets.ims.dto.ProductResponseDTO;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody Product product){
		ProductResponseDTO savedProduct = productService.createProduct(product);
		return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> getProducts(){
		List<ProductResponseDTO> products = productService.getAllProducts();
		return new ResponseEntity<List<ProductResponseDTO>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId){
		ProductResponseDTO product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProductById(@PathVariable Long productId){
		productService.deleteProductById(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductResponseDTO>> getAllProductsByCategoryId(@PathVariable Long categoryId){
		List<ProductResponseDTO> products = productService.getAllProductsByCategoryId(categoryId);
		return new ResponseEntity<List<ProductResponseDTO>>(products,HttpStatus.OK);
	}
}
