package com.jhgadgets.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jhgadgets.ims.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
