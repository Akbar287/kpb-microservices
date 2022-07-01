package com.kpb.marketservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155328472559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;

    @Column(name = "farmer_id", nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    @NotEmpty(message = "farmer_id cannot be empty")
    private Long farmer_id;

    @Column(name = "isPublished")
    private boolean isPublished;

    @Column(name = "product_name", nullable = false)
    @NotNull(message = "product name cannot be null")
    @NotEmpty(message = "product name cannot be empty")
    private int product_name;

    @Column(name = "unit", nullable = false)
    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be empty")
    private String unit;

    @Column(name = "price", nullable = false)
    @NotNull(message = "price cannot be null")
    @NotEmpty(message = "price cannot be empty")
    private BigInteger price;

    @Column(name = "file_image", nullable = false)
    @NotNull(message = "file image cannot be null")
    @NotEmpty(message = "file image cannot be empty")
    private String file_image;

    @Column(name = "stock", nullable = false)
    @NotNull(message = "stock cannot be null")
    @NotEmpty(message = "stock cannot be empty")
    private int stock;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;
}
