package com.kpb.memberservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tanggungan_petani")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TanggunganPetani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752559803L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tanggunganPetaniId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "keluarga_petani_id", nullable = false)
    private KeluargaPetani keluargaPetani;

    @Column(nullable = false, length = 64)
    @NotNull(message = "children name type cannot be null")
    @NotEmpty(message = "children name type cannot be empty")
    private String namaAnak;

    @Column(nullable = false)
    @NotNull(message = "children number type cannot be null")
    private int nomorAnak;

    @Column(nullable = false, length = 32)
    @NotNull(message = "Place Birth cannot be null")
    @NotEmpty(message = "Place Birth cannot be empty")
    private String tempatLahir;

    @Column(nullable = false)
    @NotNull(message = "Date Birth cannot be null")
    private LocalDate tanggalLahir;

    @Column(nullable = false, length = 16)
    @NotNull(message = "education cannot be null")
    @NotEmpty(message = "education cannot be empty")
    private String pendidikan;
}
