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

import com.jhgadgets.ims.dto.PurchaseResponseDTO;
import com.jhgadgets.ims.model.Purchase;
import com.jhgadgets.ims.service.PurchaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController(PurchaseService purchaseService) {
		super();
		this.purchaseService = purchaseService;
	}
	
	@PostMapping
	public ResponseEntity<PurchaseResponseDTO> createPurchase(@Valid @RequestBody Purchase purchase){
		return new ResponseEntity<>(purchaseService.createPurchase(purchase),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchases(){
		return new ResponseEntity<List<PurchaseResponseDTO>>(purchaseService.getAllPurchases(),HttpStatus.OK);
	}
	
	@GetMapping("/{purchaseId}")
	public ResponseEntity<PurchaseResponseDTO> getPurchaseById(@PathVariable Long purchaseId){
		return new ResponseEntity<>(purchaseService.getPurchaseById(purchaseId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{purchaseId}")
	public ResponseEntity<Void> deletePurchaseById(@PathVariable Long purchaseId){
		purchaseService.deletePurchaseById(purchaseId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
