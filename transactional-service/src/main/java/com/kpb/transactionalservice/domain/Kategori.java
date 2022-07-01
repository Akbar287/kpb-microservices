package com.kpb.transactionalservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "kategori")
public class Kategori implements Serializable {
    @Serial
    private static final long serialVersionUID = -6298555325752573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kategoriId;

    @JsonIgnore
    @OneToMany(mappedBy = "kategori")
    private List<Produk> produk;

    @Column(nullable = false)
    @NotNull(message = "category_name cannot be null")
    @NotEmpty(message = "category_name cannot be empty")
    private String namaKategori;

    @Column(nullable = false)
    @NotNull(message = "file_image cannot be null")
    @NotEmpty(message = "file_image cannot be empty")
    private String fileGambar;

    private String deskripsi;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
