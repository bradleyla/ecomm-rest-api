package com.cogent.ecommrestapi.repository;

import com.cogent.ecommrestapi.entity.Product;
import com.cogent.ecommrestapi.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategoryCategoryId(Long categoryId);
}
