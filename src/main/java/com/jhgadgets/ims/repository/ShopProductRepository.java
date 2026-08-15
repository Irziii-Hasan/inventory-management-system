package com.jhgadgets.ims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhgadgets.ims.model.ShopProduct;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Long> {
	
	List<ShopProduct> findByProductId(Long productId);

}
