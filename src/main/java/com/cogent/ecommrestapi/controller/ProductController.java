package com.cogent.ecommrestapi.controller;

import com.cogent.ecommrestapi.entity.Product;
import com.cogent.ecommrestapi.entity.SubCategory;
import com.cogent.ecommrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/categories")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{categoryId}/{subCategoryId}/products")
    public ResponseEntity<Product> createProduct(@PathVariable("categoryId") Long categoryId,
                                                         @PathVariable("subCategoryId") Long subCategoryId,
                                                         @Valid @RequestBody Product product) {
        Product data = productService.createProduct(categoryId, subCategoryId, product);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/{subCategoryId}/{productId}")
    public ResponseEntity<Product> getProductByCategoryAndSubCategoryId(@PathVariable("categoryId") Long categoryId,
                                                 @PathVariable("subCategoryId") Long subCategoryId,
                                                 @PathVariable("productId") Long productId) {
        Product data = productService.getProductByCategoryAndSubCategoryId(categoryId, subCategoryId, productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Product data = productService.getProductById(productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/subcategory/{subCategoryId}/products")
    public ResponseEntity<List<Product>> getProductsBySubCategoryId(@PathVariable("subCategoryId") Long subCategoryId) {
        List<Product> data = productService.getProductsBySubCategoryId(subCategoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Product> data = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> data = productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                                 @Valid @RequestBody Product product) {
        Product data = productService.updateProduct(productId, product);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
