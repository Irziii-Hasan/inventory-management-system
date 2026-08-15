package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.ShopProductResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.ShopProductMapper;
import com.jhgadgets.ims.model.Product;
import com.jhgadgets.ims.model.Shop;
import com.jhgadgets.ims.model.ShopProduct;
import com.jhgadgets.ims.repository.ProductRepository;
import com.jhgadgets.ims.repository.ShopProductRepository;
import com.jhgadgets.ims.repository.ShopRepository;
import com.jhgadgets.ims.service.ShopProductService;

@Service
public class ShopProductServiceImpl implements ShopProductService {

	private final ShopProductRepository shopProductRepository;
	private final ShopProductMapper shopProductMapper;
	private final ShopRepository shopRepository;
	private final ProductRepository productRepository;
	private static final Logger logger = LoggerFactory.getLogger(ShopProductServiceImpl.class);
	
	public ShopProductServiceImpl(ShopProductRepository shopProductRepository, ShopProductMapper shopProductMapper, ShopRepository shopRepository, ProductRepository productRepository) {
		super();
		this.shopProductRepository = shopProductRepository;
		this.shopProductMapper = shopProductMapper;
		this.shopRepository = shopRepository;
		this.productRepository = productRepository;
	}

	@Override
	public ShopProductResponseDTO createShopProduct(ShopProduct shopProduct) {
		Product product = productRepository.findById(shopProduct.getProduct().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", shopProduct.getProduct().getId()));
		
		Shop shop = shopRepository.findById(shopProduct.getShop().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Shop","id",shopProduct.getShop().getId()));
		
		shopProduct.setProduct(product);
		shopProduct.setShop(shop);
		ShopProduct savedShopProduct = shopProductRepository.save(shopProduct);
		logger.info("ShopProduct created with id: {}", savedShopProduct.getId());
		return shopProductMapper.toDto(savedShopProduct);
	}

	@Override
	public ShopProductResponseDTO getShopProductById(Long shopProductId) {
		return shopProductMapper.toDto(shopProductRepository.findById(shopProductId)
				.orElseThrow(()-> new ResourceNotFoundException("ShopProduct", "id", shopProductId)));
	}

	@Override
	public List<ShopProductResponseDTO> getAllShopProducts() {
		return shopProductMapper.toDtoList(shopProductRepository.findAll());
	}

	@Override
	public void deleteShopProductById(Long shopProductId) {
		ShopProduct shopProduct = shopProductRepository.findById(shopProductId)
				.orElseThrow(()-> new ResourceNotFoundException("ShopProduct", "id", shopProductId));
		shopProductRepository.deleteById(shopProduct.getId());
	}

	@Override
	public List<ShopProductResponseDTO> getShopProductByProductId(Long productId) {
		return shopProductMapper.toDtoList(shopProductRepository.findByProductId(productId));
	}

}
