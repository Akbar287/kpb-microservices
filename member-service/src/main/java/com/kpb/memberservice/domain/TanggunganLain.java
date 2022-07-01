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
@Table(name = "tanggungan_lain")
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TanggunganLain implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752559804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tanggunganLainId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "keluarga_petani_id", nullable = false)
    private KeluargaPetani keluargaPetani;

    @Column(nullable = false, length = 64)
    @NotNull(message = "full name type cannot be null")
    @NotEmpty(message = "full name type cannot be empty")
    private String namaLengkap;

    @Column(nullable = false, length = 32)
    @NotNull(message = "Place Birth cannot be null")
    @NotEmpty(message = "Place Birth cannot be empty")
    private String tempatLahir;

    @Column(nullable = false)
    @NotNull(message = "Date Birth cannot be null")
    private LocalDate tanggalLahir;

    @Column(nullable = false)
    @NotNull(message = "relationship patriarch cannot be null")
    @NotEmpty(message = "relationship patriarch cannot be empty")
    private String hubunganKepalaKeluarga;

}
