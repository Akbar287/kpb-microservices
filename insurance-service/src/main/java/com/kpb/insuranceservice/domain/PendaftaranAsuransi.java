package com.kpb.insuranceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pendaftaran_asuransi")
public class PendaftaranAsuransi implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283157825752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pendaftaranAsuransiId;

    @JsonIgnore
    @OneToMany(mappedBy = "pendaftaranAsuransi")
    private List<Penagihan> penagihan;

    @JsonIgnore
    @OneToMany(mappedBy = "pendaftaranAsuransi")
    private List<Klaim> klaim;

    @JsonIgnore
    @OneToMany(mappedBy = "pendaftaranAsuransi")
    private List<UsahaTani> usahaTani;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "asuransi_id")
    private Asuransi asuransi;

    @JsonIgnore
    @ManyToMany(mappedBy = "pendaftaranAsuransi")
    private List<DaftarPeserta> daftarPeserta;

    @Column( nullable = false)
    @NotNull(message = "insurance number cannot be null")
    @NotEmpty(message = "insurance number cannot be empty")
    private String nomorAsuransi;

    @Column( nullable = false)
    @NotNull(message = "year cannot be null")
    private int tahun;

    @Column( nullable = false)
    @NotNull(message = "planting time cannot be null")
    private int masaTanam;

    @Column( nullable = false)
    @NotNull(message = "insurance effective date cannot be null")
    @NotEmpty(message = "insurance effective date cannot be empty")
    private LocalDate waktuEfektif;

    @Column( nullable = false)
    @NotNull(message = "insurance expired date cannot be null")
    @NotEmpty(message = "insurance expired date cannot be empty")
    private LocalDate waktuBerakhir;

    @Column( nullable = false)
    @NotNull(message = "payment option cannot be null")
    @NotEmpty(message = "payment option cannot be empty")
    private String metodePembayaran;

    @Column( nullable = false)
    @NotNull(message = "total amount cannot be null")
    @NotEmpty(message = "total amount cannot be empty")
    private BigInteger totalPembayaran;

    @Column( nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String namaFile;

    @Column( nullable = false)
    @NotNull(message = "file upload cannot be null")
    @NotEmpty(message = "file upload cannot be empty")
    private String namaDokumen;

    @Column( nullable = false)
    private boolean isAktif;

    private String informasi;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;
}
