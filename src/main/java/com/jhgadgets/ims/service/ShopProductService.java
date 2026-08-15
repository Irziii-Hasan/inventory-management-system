package com.jhgadgets.ims.service;

import java.util.List;

import com.jhgadgets.ims.dto.ShopProductResponseDTO;
import com.jhgadgets.ims.model.ShopProduct;

public interface ShopProductService {
	ShopProductResponseDTO createShopProduct(ShopProduct shopProduct);
	ShopProductResponseDTO getShopProductById(Long shopProductId);
	List<ShopProductResponseDTO> getAllShopProducts();
	List<ShopProductResponseDTO> getShopProductByProductId(Long productId);
	void deleteShopProductById(Long shopProductId);


}
