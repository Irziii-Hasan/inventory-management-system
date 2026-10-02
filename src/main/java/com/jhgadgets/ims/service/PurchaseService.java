package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.PurchaseResponseDTO;
import com.jhgadgets.ims.dto.PurchaseUpdateRequest;
import com.jhgadgets.ims.model.Purchase;

public interface PurchaseService {
	
	PurchaseResponseDTO createPurchase(Purchase purchase);
	PurchaseResponseDTO getPurchaseById(Long purchaseId);
	List<PurchaseResponseDTO> getAllPurchases();
	void deletePurchaseById(Long purchaseId);
	PurchaseResponseDTO updatePurchaseDate(Long purchaseId, PurchaseUpdateRequest purchase);

}
