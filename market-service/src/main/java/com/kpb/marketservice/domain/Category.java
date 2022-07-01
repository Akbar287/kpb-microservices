package com.kpb.marketservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252557659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Column(name = "category_name", nullable = false)
    @NotNull(message = "category_name cannot be null")
    @NotEmpty(message = "category_name cannot be empty")
    private String category_name;

    @Column(name = "description")
    private String description;

    @Column(name = "file_image", nullable = false)
    @NotNull(message = "file_image cannot be null")
    @NotEmpty(message = "file_image cannot be empty")
    private String file_image;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
