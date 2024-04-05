package com.cogent.ecommrestapi.service;

import com.cogent.ecommrestapi.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Long categoryId, Long subCategoryId, Product newProduct);
    Product getProductByCategoryAndSubCategoryId(Long categoryId, Long subCategoryId, Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsBySubCategoryId(Long subCategoryId);
    List<Product> getProductsByCategoryId(Long categoryId);
    Product updateProduct(Long productId, Product updateProduct);
    void deleteProduct(Long productId);
}
