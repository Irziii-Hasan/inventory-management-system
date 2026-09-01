package com.jhgadgets.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhgadgets.ims.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
