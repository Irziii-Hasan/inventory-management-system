package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.InventoryResponseDTO;
import com.jhgadgets.ims.model.Inventory;
import com.jhgadgets.ims.model.Purchase;

public interface InventoryService {
	InventoryResponseDTO createInventory(Inventory inventory);
	InventoryResponseDTO getInventoryById(Long inventoryId);
	InventoryResponseDTO getInventoryByProductId(Long productId);
	List<InventoryResponseDTO> getAllInventories();
	void deleteInventoryById(Long inventoryId);
	
	void updateInventoryAfterPurchase(Purchase purchase);
}
