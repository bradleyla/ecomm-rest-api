package com.cogent.ecommrestapi.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "sub_categories",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "name" }
                )
        }
)
public class SubCategory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long subCategoryId;

    @NotEmpty(message = "sub category name should not be empty or null")
    @Column(name = "sub_category_name", nullable = false, unique = true)
    private String subCategoryName;

    @Column(name = "sub_category_image", nullable = true)
    private String subCategoryImage;

    @Column(name = "sub_category_description", nullable = false)
    private String subCategoryDescription;

    private Boolean status;

    private Integer position;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="categoryId")
    @JsonManagedReference(value = "subCategory-category")
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="productId")
    @JsonBackReference(value = "subCategory-product")
    @OneToMany(
            mappedBy = "subCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new HashSet<>();
}
