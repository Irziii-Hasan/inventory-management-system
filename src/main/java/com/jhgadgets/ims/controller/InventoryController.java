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

import com.jhgadgets.ims.dto.InventoryResponseDTO;
import com.jhgadgets.ims.model.Inventory;
import com.jhgadgets.ims.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
	
	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		super();
		this.inventoryService = inventoryService;
	}
		
	@PostMapping
	public ResponseEntity<InventoryResponseDTO> createInventory(@Valid @RequestBody Inventory inventory){
		InventoryResponseDTO savedInventory=  inventoryService.createInventory(inventory);
		return new ResponseEntity<>(savedInventory,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryResponseDTO>> getAllInventories(){
		List<InventoryResponseDTO> inventories = inventoryService.getAllInventories();
		return new ResponseEntity<List<InventoryResponseDTO>>(inventories,HttpStatus.OK);
	}

	@GetMapping("/{inventoryId}")
	public ResponseEntity<InventoryResponseDTO> getInventoryById(@PathVariable Long inventoryId){
		return new ResponseEntity<>(inventoryService.getInventoryById(inventoryId),HttpStatus.OK);
	}
	
	@GetMapping("product/{productId}")
	public ResponseEntity<InventoryResponseDTO> getInventoryByProductId(@PathVariable Long productId){
		return new ResponseEntity<>(inventoryService.getInventoryByProductId(productId), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{inventoryId}")
	public ResponseEntity<Void> deleteInventoryById(@PathVariable Long inventoryId){
		inventoryService.deleteInventoryById(inventoryId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
}
