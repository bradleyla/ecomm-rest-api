package com.cogent.ecommrestapi.service.impl;

import com.cogent.ecommrestapi.entity.Category;
import com.cogent.ecommrestapi.entity.Product;
import com.cogent.ecommrestapi.entity.SubCategory;
import com.cogent.ecommrestapi.exception.ECommerceApiException;
import com.cogent.ecommrestapi.exception.ResourceNotFoundException;
import com.cogent.ecommrestapi.repository.CategoryRepository;
import com.cogent.ecommrestapi.repository.ProductRepository;
import com.cogent.ecommrestapi.repository.SubCategoryRepository;
import com.cogent.ecommrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Long categoryId, Long subCategoryId, Product newProduct) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        newProduct.setCategory(category);
        newProduct.setSubCategory(subCategory);
        System.out.println(newProduct.getCategory().getCategoryName());
        System.out.println(newProduct.getSubCategory().getSubCategoryName());
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductByCategoryAndSubCategoryId(Long categoryId, Long subCategoryId, Long productId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(!subCategory.getCategory().getCategoryId().equals(category.getCategoryId()))
            throw new RuntimeException("SubCategory does not belong to this category");
        if(!product.getCategory().getCategoryId().equals(category.getCategoryId()))
            throw new RuntimeException("Product does not belong to this category");
        if(!product.getSubCategory().getSubCategoryId().equals(subCategory.getSubCategoryId()))
            throw new RuntimeException("Product does not belong to this subcategory");
        return product;
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getProductsBySubCategoryId(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Product> products = productRepository.findBySubCategorySubCategoryId(subCategoryId);
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Product> products = productRepository.findByCategoryCategoryId(categoryId);
        return products;
    }

    @Override
    public Product updateProduct(Long productId, Product updateProduct) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setCategory(updateProduct.getCategory());
        product.setSubCategory(updateProduct.getSubCategory());
        product.setProductName(updateProduct.getProductName());
        product.setProductDescription(updateProduct.getProductDescription());
        product.setProductPrice(updateProduct.getProductPrice());
        product.setProductImage(updateProduct.getProductImage());
        product.setPosition(updateProduct.getPosition());
        product.setStatus(updateProduct.getStatus());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);

    }
}
