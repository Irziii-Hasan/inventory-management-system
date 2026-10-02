package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.ShopResponseDTO;
import com.jhgadgets.ims.dto.ShopUpdateRequest;
import com.jhgadgets.ims.model.Shop;

public interface ShopService {
	ShopResponseDTO createShop (Shop shop);
	ShopResponseDTO getShopById(Long shopId);
	List<ShopResponseDTO> getAllShops();
	ShopResponseDTO updateShop(Long shopId, ShopUpdateRequest request);
	void deleteShopById(Long shopId);

}
