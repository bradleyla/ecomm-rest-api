package com.cogent.ecommrestapi.service;

import com.cogent.ecommrestapi.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    SubCategory createSubCategory(Long categoryId, SubCategory newSubCategory);
    List<SubCategory> getSubCategoriesByCategoryId(Long categoryId);
    SubCategory getSubCategoryById(Long categoryId, Long subCategoryId);
    SubCategory updateSubCategory( Long subCategoryId, SubCategory updateSubCategory);
    void deleteSubCategory(Long subCategoryId);
}
