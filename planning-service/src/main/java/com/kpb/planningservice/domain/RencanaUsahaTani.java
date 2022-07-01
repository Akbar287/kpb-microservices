package com.kpb.planningservice.domain;

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
@Table(name = "rencana_usaha_tani")
public class RencanaUsahaTani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283432126252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rencanaUsahaTaniId;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<BiayaUsahaTani> biayaUsahaTani;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<JenisTanaman> jenisTanaman;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<KekuranganPupuk> kekuranganPupuk;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<RealisasiPupukSubsidi> realisasiPupukSubsidi;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<KebutuhanSaprotan> kebutuhanSaprotan;

    @OneToMany(mappedBy = "rencanaUsahaTani")
    private List<JadwalUsahaTani> jadwalUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    private Long petaniId;

    @Column(nullable = false)
    private Integer tahun;

    @Column(nullable = false)
    @NotNull(message = "area cannot be null")
    private Integer luasLahan;

    @Column(nullable = false)
    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be empty")
    private String status;

    @Column(nullable = false)
    private BigInteger totalBiayaUsahaTani;

    @Column(nullable = false)
    private BigInteger grandTotal;

    @Column(nullable = false)
    private BigInteger prediksiPendapatan;

    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
