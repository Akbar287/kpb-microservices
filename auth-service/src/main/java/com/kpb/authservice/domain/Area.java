package com.kpb.authservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "area")
public class Area implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325737559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User user;

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
