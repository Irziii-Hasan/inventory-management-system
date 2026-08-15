package com.jhgadgets.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhgadgets.ims.model.Mall;

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {

}
