package com.kpb.informationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "categories")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "title", nullable = false)
    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Column(name = "slug", nullable = false)
    @NotNull(message = "slug cannot be null")
    @NotEmpty(message = "slug cannot be empty")
    private String slug;

    @Column(name = "description", nullable = false)
    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String description;

    @Column(name = "content", nullable = false)
    @NotNull(message = "content cannot be null")
    @NotEmpty(message = "content cannot be empty")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "published_at", nullable = false)
    private LocalDate published_at;

}
