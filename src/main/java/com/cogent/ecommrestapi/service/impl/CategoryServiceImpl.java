package com.cogent.ecommrestapi.service.impl;

import com.cogent.ecommrestapi.entity.Category;
import com.cogent.ecommrestapi.exception.ResourceNotFoundException;
import com.cogent.ecommrestapi.repository.CategoryRepository;
import com.cogent.ecommrestapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return category;
    }

    @Override
    public Category updateCategory(Long categoryId, Category updateCategory) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setCategoryName(updateCategory.getCategoryName());
        category.setCategoryDescription(updateCategory.getCategoryDescription());
        category.setCategoryImage(updateCategory.getCategoryImage());
        category.setPosition(updateCategory.getPosition());
        category.setStatus(updateCategory.getStatus());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.deleteById(categoryId);
    }
}
