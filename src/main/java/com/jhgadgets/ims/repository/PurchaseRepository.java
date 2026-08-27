package com.jhgadgets.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhgadgets.ims.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
