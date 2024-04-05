package com.cogent.ecommrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        name = "categories"
)
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long categoryId;

    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @Column(name = "category_description", nullable = false)
    private String categoryDescription;

    @Column(name = "category_image")
    private String categoryImage;

    private Integer position;

    private Boolean status;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="productId")
    @JsonBackReference(value = "category-product")
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new HashSet<>();

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="subCategoryId")
    @JsonBackReference(value = "subCategory-category")
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SubCategory> subCategories = new HashSet<>();
}
