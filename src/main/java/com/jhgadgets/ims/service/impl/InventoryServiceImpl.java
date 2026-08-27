package com.jhgadgets.ims.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.InventoryResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.InventoryMapper;
import com.jhgadgets.ims.model.Inventory;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.model.Purchase;
import com.jhgadgets.ims.repository.InventoryRepository;
import com.jhgadgets.ims.repository.ProductRepository;
import com.jhgadgets.ims.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	private final InventoryMapper inventoryMapper;
	private final ProductRepository productRepository;
	private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
	

	public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, ProductRepository productRepository) {
		super();
		this.inventoryRepository = inventoryRepository;
		this.inventoryMapper = inventoryMapper;
		this.productRepository = productRepository;
	}

	@Override
	public InventoryResponseDTO createInventory(Inventory inventory) {
		Product savedProduct = productRepository.findById(inventory.getProduct().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Product", "Id", inventory.getProduct().getId()));
		inventory.setProduct(savedProduct);
		Inventory savedInventory = inventoryRepository.save(inventory);
		logger.info("Inventory created with id: {}",savedInventory.getId());
		
		return inventoryMapper.toDto(savedInventory);
	}

	@Override
	public InventoryResponseDTO getInventoryById(Long inventoryId) {
		return inventoryMapper.toDto(inventoryRepository.findById(inventoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Inventory", "id", inventoryId)));
	}

	@Override
	public InventoryResponseDTO getInventoryByProductId(Long productId) {
		return inventoryMapper.toDto(inventoryRepository.findByProductId(productId)
				.orElseThrow(()-> new ResourceNotFoundException("Inventory", "productId", productId)));
	}

	@Override
	public List<InventoryResponseDTO> getAllInventories() {
		return inventoryMapper.toDtoList(inventoryRepository.findAll());
	}

	@Override
	public void deleteInventoryById(Long inventoryId) {
		Inventory inventory = inventoryRepository.findById(inventoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Inventory", "id", inventoryId));
		inventoryRepository.deleteById(inventory.getId());

	}

	@Override
	public void updateInventoryAfterPurchase(Purchase purchase) {
		Optional<Inventory> existingInventory = inventoryRepository.findByProductId(purchase.getProduct().getId());
		if(existingInventory.isPresent()) {
			Inventory inventory = existingInventory.get();
			BigDecimal oldCost = inventory.getAverageCost();
			Integer oldQuantity = inventory.getQuantity();
			
			BigDecimal currentPurchaseCost = purchase.getPurchasePrice();
			Integer currentPurchaseQuantity = purchase.getQuantity();
			
//			calculating total quantity and average cost
//			a = oldCost × oldQuantity
//			b = currentPurchaseCost × currentPurchaseQuantity
//			c = a + b
//			currentQuantity = oldQuantity + currentPurchaseQuantity
//			avgCost = c / currentQuantity
			
			
			BigDecimal oldTotalCost = oldCost.multiply(BigDecimal.valueOf(oldQuantity));
			BigDecimal currentTotalCost = currentPurchaseCost.multiply(BigDecimal.valueOf(currentPurchaseQuantity));
			
			BigDecimal totalCost = oldTotalCost.add(currentTotalCost);
			Integer totalQuantity = oldQuantity + currentPurchaseQuantity;
			BigDecimal avgCost = totalCost.divide(BigDecimal.valueOf(totalQuantity), 2, RoundingMode.HALF_UP);
			
			inventory.setAverageCost(avgCost);
			inventory.setQuantity(totalQuantity);
			Inventory savedInventory = inventoryRepository.save(inventory);
			logger.info("Inventory updated with id: {}",savedInventory.getId());
			
		}else {
			Inventory inventory = new Inventory();
			inventory.setAverageCost(purchase.getPurchasePrice());
			inventory.setProduct(purchase.getProduct());
			inventory.setQuantity(purchase.getQuantity());
			Inventory savedInventory = inventoryRepository.save(inventory);
			logger.info("Inventory created with id: {}",savedInventory.getId());
		}
	}

}
