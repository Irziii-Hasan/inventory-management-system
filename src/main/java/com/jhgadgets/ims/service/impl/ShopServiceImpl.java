package com.jhgadgets.ims.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhgadgets.ims.dto.ShopResponseDTO;
import com.jhgadgets.ims.exception.ResourceNotFoundException;
import com.jhgadgets.ims.mapper.ShopMapper;
import com.jhgadgets.ims.model.Mall;
import com.jhgadgets.ims.model.Shop;
import com.jhgadgets.ims.repository.MallRepository;
import com.jhgadgets.ims.repository.ShopRepository;
import com.jhgadgets.ims.service.ShopService;
@Service
public class ShopServiceImpl implements ShopService {
	
	private final ShopRepository shopRepository;
	private final ShopMapper shopMapper;
	private final MallRepository mallRepository;
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	public ShopServiceImpl(ShopRepository shopRepository, ShopMapper shopMapper, MallRepository mallRepository) {
		super();
		this.shopRepository = shopRepository;
		this.shopMapper = shopMapper;
		this.mallRepository = mallRepository;
	}

	@Override
	public ShopResponseDTO createShop(Shop shop) {
		Mall mall = mallRepository.findById(shop.getMall().getId())
				.orElseThrow(()-> new ResourceNotFoundException("Mall", "id", shop.getMall().getId()));
		shop.setMall(mall);
		Shop savedShop = shopRepository.save(shop);
		logger.info("Shop created with id: {}",savedShop.getId());
		return shopMapper.toDto(savedShop);
	}

	@Override
	public ShopResponseDTO getShopById(Long shopId) {
		return shopMapper.toDto(shopRepository.findById(shopId)
				.orElseThrow(()-> new ResourceNotFoundException("Shop", "id", shopId)));
	}

	@Override
	public List<ShopResponseDTO> getAllShops() {
		return shopMapper.toDtoList(shopRepository.findAll());
	}

	@Override
	public void deleteShopById(Long shopId) {
		Shop shop = shopRepository.findById(shopId)
				.orElseThrow(()-> new ResourceNotFoundException("Shop", "id", shopId));
		shopRepository.deleteById(shop.getId());
	}

}
