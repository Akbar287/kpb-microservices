package com.kpb.informationservice.domain;

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
@Table(name ="tags")
public class Tag implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag_id;

    @Column(name = "title", nullable = false)
    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Column(name = "meta_title", nullable = false)
    @NotNull(message = "meta title cannot be null")
    @NotEmpty(message = "meta title cannot be empty")
    private String meta_title;

    @Column(name = "slug", nullable = false)
    @NotNull(message = "slug cannot be null")
    @NotEmpty(message = "slug cannot be empty")
    private String slug;

    @Column(name = "content", nullable = false)
    @NotNull(message = "content cannot be null")
    @NotEmpty(message = "content cannot be empty")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;

    @ManyToMany(mappedBy = "tagList")
    private List<Information> informationList;
}
