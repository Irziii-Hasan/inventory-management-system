package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhgadgets.ims.dto.PurchaseResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.PurchaseMapper;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.model.Purchase;
import com.jhgadgets.ims.model.Shop;
import com.jhgadgets.ims.repository.ProductRepository;
import com.jhgadgets.ims.repository.PurchaseRepository;
import com.jhgadgets.ims.repository.ShopRepository;
import com.jhgadgets.ims.service.InventoryService;
import com.jhgadgets.ims.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	private final PurchaseMapper purchaseMapper;
	private final ProductRepository productRepository;
	private final ShopRepository shopRepository;
	private final InventoryService inventoryService;
	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper, ShopRepository shopRepository, ProductRepository productRepository, InventoryService inventoryService) {
		super();
		this.purchaseRepository = purchaseRepository;
		this.purchaseMapper = purchaseMapper;
		this.productRepository = productRepository;
		this.shopRepository = shopRepository;
		this.inventoryService = inventoryService;
	}

	@Transactional
	@Override
	public PurchaseResponseDTO createPurchase(Purchase purchase) {
		Product product = productRepository.findById(purchase.getProduct().getId())
		.orElseThrow(()-> new ResourceNotFoundException("Product", "id", purchase.getProduct().getId()));
		
		Shop shop = shopRepository.findById(purchase.getShop().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Shop", "id", purchase.getShop().getId()));
		purchase.setProduct(product);
		purchase.setShop(shop);
		Purchase savedPurchase = purchaseRepository.save(purchase);
		inventoryService.updateInventoryAfterPurchase(savedPurchase);
		logger.info("Purchase created with id: {}", savedPurchase.getId());
		return purchaseMapper.toDto(savedPurchase);
	}

	@Override
	public PurchaseResponseDTO getPurchaseById(Long purchaseId) {
		return purchaseMapper.toDto(purchaseRepository.findById(purchaseId)
				.orElseThrow(()-> new ResourceNotFoundException("Purchase", "id", purchaseId)));
		}

	@Override
	public List<PurchaseResponseDTO> getAllPurchases() {
		return purchaseMapper.toDtoList(purchaseRepository.findAll());
	}

	@Override
	public void deletePurchaseById(Long purchaseId) {
		Purchase purchase = purchaseRepository.findById(purchaseId)
				.orElseThrow(()-> new ResourceNotFoundException("Purchase", "id", purchaseId));
		purchaseRepository.deleteById(purchase.getId());

	}

}
