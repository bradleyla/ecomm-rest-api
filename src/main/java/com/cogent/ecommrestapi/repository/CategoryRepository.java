package com.cogent.ecommrestapi.repository;

import com.cogent.ecommrestapi.entity.Category;
import com.cogent.ecommrestapi.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
