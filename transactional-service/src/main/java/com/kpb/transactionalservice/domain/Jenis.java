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
@Table(name = "jenis")
public class Jenis implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283198755752573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jenisId;

    @JsonIgnore
    @OneToMany(mappedBy = "jenis")
    private List<Produk> produk;

    @Column(nullable = false)
    @NotNull(message = "type_name cannot be null")
    @NotEmpty(message = "type_name cannot be empty")
    private String namaJenis;

    @Column(nullable = false)
    @NotNull(message = "file_image cannot be null")
    @NotEmpty(message = "file_image cannot be empty")
    private String fileGambar;

    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
