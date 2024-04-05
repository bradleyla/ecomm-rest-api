package com.cogent.ecommrestapi.service;

import com.cogent.ecommrestapi.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category newCategory);
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category updateCategory(Long categoryId, Category updateCategory);
    void deleteCategory(Long categoryId);
}
