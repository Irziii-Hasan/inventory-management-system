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

import com.jhgadgets.ims.dto.ShopProductResponseDTO;
import com.jhgadgets.ims.model.ShopProduct;
import com.jhgadgets.ims.service.ShopProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shop-products")
public class ShopProductController {

	private final ShopProductService shopProductService;

	public ShopProductController(ShopProductService shopProductService) {
		super();
		this.shopProductService = shopProductService;
	}
	
	@PostMapping
	public ResponseEntity<ShopProductResponseDTO> createShopProduct(@Valid @RequestBody ShopProduct product){
		ShopProductResponseDTO savedProduct = shopProductService.createShopProduct(product);
		return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ShopProductResponseDTO>> getShopProducts(){
		List<ShopProductResponseDTO> shopProduct = shopProductService.getAllShopProducts();
		return new ResponseEntity<List<ShopProductResponseDTO>>(shopProduct,HttpStatus.OK);
	}
	
	@GetMapping("/{shopProductId}")
	public ResponseEntity<ShopProductResponseDTO> getShopProductById(@PathVariable Long shopProductId){
		ShopProductResponseDTO shopProducts = shopProductService.getShopProductById(shopProductId);
		return new ResponseEntity<>(shopProducts, HttpStatus.OK);
	}
	
	@DeleteMapping("/{shopProductId}")
	public ResponseEntity<Void> deleteShopProductById(@PathVariable Long shopProductId){
		shopProductService.deleteShopProductById(shopProductId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ShopProductResponseDTO>> getShopProductByProductId(@PathVariable Long productId){
		List<ShopProductResponseDTO> shopProduct = shopProductService.getShopProductByProductId(productId);
		return new ResponseEntity<List<ShopProductResponseDTO>>(shopProduct,HttpStatus.OK);
	}
}
