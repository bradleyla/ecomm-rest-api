package com.cogent.ecommrestapi.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        name = "products"
)
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long productId;

    @NotEmpty(message = "product name should not be empty or null")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotEmpty(message = "product price should not be empty or null")
    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "product_image")
    private String productImage;

    private Integer position;

    private Boolean status;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="categoryId")
    @JsonManagedReference(value = "category-product")
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="subCategoryId")
    @JsonManagedReference(value = "subCategory-product")
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(
            name = "sub_category_id",
            nullable = false
    )
    private SubCategory subCategory;
}
