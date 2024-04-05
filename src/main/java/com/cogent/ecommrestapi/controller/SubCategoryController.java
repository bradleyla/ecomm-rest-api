package com.cogent.ecommrestapi.controller;

import com.cogent.ecommrestapi.entity.SubCategory;
import com.cogent.ecommrestapi.service.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/categories")
@RestController
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/{categoryId}/subCategories")
    public ResponseEntity<SubCategory> createSubCategory(@PathVariable("categoryId") Long categoryId,
                                                         @Valid @RequestBody SubCategory subCategory) {
        SubCategory data = subCategoryService.createSubCategory(categoryId, subCategory);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/subCategories")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<SubCategory> data = subCategoryService.getSubCategoriesByCategoryId(categoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("categoryId") Long categoryId,
                                                          @PathVariable("subCategoryId") Long subCategoryId) {
        SubCategory data = subCategoryService.getSubCategoryById(categoryId, subCategoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable("categoryId") Long categoryId,
                                                         @PathVariable("subCategoryId") Long subCategoryId,
                                                         @Valid @RequestBody SubCategory subCategory) {
        SubCategory data = subCategoryService.updateSubCategory(subCategoryId, subCategory);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("categoryId") Long categoryId,
                                                    @PathVariable("subCategoryId") Long subCategoryId) {
        subCategoryService.deleteSubCategory(subCategoryId);
        return new ResponseEntity<>("Subcategory deleted successfully", HttpStatus.OK);
    }
}
