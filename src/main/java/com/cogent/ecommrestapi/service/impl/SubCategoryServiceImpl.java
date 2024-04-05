package com.cogent.ecommrestapi.service.impl;

import com.cogent.ecommrestapi.entity.Category;
import com.cogent.ecommrestapi.entity.SubCategory;
import com.cogent.ecommrestapi.exception.ECommerceApiException;
import com.cogent.ecommrestapi.exception.ResourceNotFoundException;
import com.cogent.ecommrestapi.repository.CategoryRepository;
import com.cogent.ecommrestapi.repository.SubCategoryRepository;
import com.cogent.ecommrestapi.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory createSubCategory(Long categoryId, SubCategory newSubCategory) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        newSubCategory.setCategory(category);
        return subCategoryRepository.save(newSubCategory);
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findByCategoryCategoryId(categoryId);
        return subCategories;
    }

    @Override
    public SubCategory getSubCategoryById(Long categoryId, Long subCategoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        if(!subCategory.getCategory().getCategoryId().equals(category.getCategoryId()))
            throw new ECommerceApiException(HttpStatus.BAD_REQUEST, "SubCategory does not belong to this category");
        return subCategory;
    }

    @Override
    public SubCategory updateSubCategory(Long subCategoryId, SubCategory updateSubCategory) {
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        subCategory.setCategory(updateSubCategory.getCategory());
        subCategory.setSubCategoryName(updateSubCategory.getSubCategoryName());
        subCategory.setSubCategoryImage(updateSubCategory.getSubCategoryImage());
        subCategory.setSubCategoryDescription(updateSubCategory.getSubCategoryDescription());
        subCategory.setStatus(updateSubCategory.getStatus());
        subCategory.setPosition(updateSubCategory.getPosition());
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        subCategoryRepository.delete(subCategory);
    }
}
