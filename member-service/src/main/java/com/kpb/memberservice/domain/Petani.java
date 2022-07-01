package com.kpb.memberservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "petani")
public class Petani implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petaniId;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poktan_id")
    private Poktan poktan;

    @Length(message = "length NIK must be 16 number", min = 16, max = 16)
    @Column(nullable = false, unique = true)
    @NotNull(message = "NIK cannot be null")
    @NotEmpty(message = "NIK cannot be empty")
    private String nik;

    @Column(nullable = false)
    @NotNull(message = "Status cannot be null")
    @NotEmpty(message = "Status cannot be empty")
    private String statusPetani;

    @Column(nullable = false, length = 6)
    @NotNull(message = "Gender cannot be null")
    @NotEmpty(message = "Gender cannot be empty")
    private String jenisKelamin;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @Column(length = 128)
    private String alamat;

    @Column(length = 32)
    private String tempatLahir;

    @Column()
    private LocalDate tanggalLahir;

    @Column(length = 16)
    private String nomorKK;

    @Column(length = 16)
    private String agama;

    @Column(length = 32)
    private String pendidikan;

    @Column(length = 14)
    private String statusPernikahan;

    @Column(length = 64)
    private String ibuKandung;

    @Column(length = 8)
    private String jenisPetani;

    @JsonIgnore
    @OneToOne(mappedBy = "petani")
    private KeluargaPetani keluargaPetani;

    @JsonIgnore
    @OneToOne(mappedBy = "petani")
    private Kepemilikan kepemilikan;

    @JsonIgnore
    @OneToMany(mappedBy = "petani")
    private List<AsetPetani> asetPetani;

    @JsonIgnore
    @OneToMany(mappedBy = "petani")
    private List<LuasLahan> luasLahan;
}
