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

import com.jhgadgets.ims.dto.ShopResponseDTO;
import com.jhgadgets.ims.model.Shop;
import com.jhgadgets.ims.service.ShopService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

	private final ShopService shopService;

	public ShopController(ShopService shopService) {
		super();
		this.shopService = shopService;
	}
	
	@PostMapping
	public ResponseEntity<ShopResponseDTO> createShop(@Valid @RequestBody Shop shop){
		ShopResponseDTO savedShop = shopService.createShop(shop);
		return new ResponseEntity<>(savedShop,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ShopResponseDTO>> getShops(){
		List<ShopResponseDTO> shops = shopService.getAllShops();
		return new ResponseEntity<List<ShopResponseDTO>>(shops,HttpStatus.OK);
	}
	
	@GetMapping("/{shopId}")
	public ResponseEntity<ShopResponseDTO> getShopById(@PathVariable Long shopId){
		ShopResponseDTO shop = shopService.getShopById(shopId);
		return new ResponseEntity<>(shop, HttpStatus.OK);
	}
	
	@DeleteMapping("/{shopId}")
	public ResponseEntity<Void> deleteShopById(@PathVariable Long shopId){
		shopService.deleteShopById(shopId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
