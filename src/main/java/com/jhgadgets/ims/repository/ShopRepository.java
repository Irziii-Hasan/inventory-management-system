package com.jhgadgets.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jhgadgets.ims.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
