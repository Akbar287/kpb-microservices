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

@Entity
@Data
@Table(name = "wilayah_kerja_distributor")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WilayahKerjaDistributor implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wilayahKerjaDistributorId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    @Column(nullable = false)
    @NotNull(message = "kodeDesa cannot be null")
    private int kodeDesa;

    @Column(nullable = false, length = 32)
    @NotNull(message = "namaDesa district cannot be null")
    private String namaDesa;

    @Column(nullable = false)
    @NotNull(message = "kodeKecamatan cannot be null")
    private int kodeKecamatan;

    @Column(nullable = false, length = 32)
    @NotNull(message = "namaKecamatan cannot be null")
    private String namaKecamatan;

    @Column(nullable = false)
    @NotNull(message = "kodeKabupaten cannot be null")
    private int kodeKabupaten;

    @Column(nullable = false, length = 32)
    @NotNull(message = "namaKabupaten cannot be null")
    private String namaKabupaten;

    @Column(nullable = false)
    @NotNull(message = "kodeProvinsi cannot be null")
    private int kodeProvinsi;

    @Column(nullable = false, length = 32)
    @NotNull(message = "namaProvinsi cannot be null")
    private String namaProvinsi;
}
