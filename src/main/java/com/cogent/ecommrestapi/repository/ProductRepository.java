package com.cogent.ecommrestapi.repository;

import com.cogent.ecommrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryCategoryId(Long categoryId);

    List<Product> findBySubCategorySubCategoryId(Long subCategoryId);
}
