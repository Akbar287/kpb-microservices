package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "jadwal_usaha_tani")
public class JadwalUsahaTani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6292545326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jadwalUsahaTaniId;

    @OneToOne(mappedBy = "jadwalUsahaTani")
    private PerkiraanJumlahPanen perkiraanJumlahPanen;

    @OneToOne(mappedBy = "jadwalUsahaTani")
    private HasilPascaPanen hasilPascaPanen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private Integer masaTanam;

    @Column(nullable = false)
    @NotNull(message = "groundwater_drainage cannot be null")
    private LocalDate airTanahDrainase;

    @Column(nullable = false)
    @NotNull(message = "land_processing cannot be null")
    private LocalDate pengolahanTanah;

    @Column(nullable = false)
    @NotNull(message = "seedbed cannot be null")
    private LocalDate persemaian;

    @Column(nullable = false)
    @NotNull(message = "planting cannot be null")
    private LocalDate penanaman;

    @Column(nullable = false)
    @NotNull(message = "maintenance cannot be null")
    private LocalDate pemeliharaan;

    @Column(nullable = false)
    @NotNull(message = "herbicide_spray cannot be null")
    private LocalDate semprotanHerbisida;

    @Column(nullable = false)
    @NotNull(message = "spraying_fungicides1_spt1 cannot be null")
    private LocalDate penyemprotanFungisidaPertama;

    @Column(nullable = false)
    @NotNull(message = "spraying_fungicides2_spt2 cannot be null")
    private LocalDate penyemprotanFungisidaKedua;

    @Column(nullable = false)
    @NotNull(message = "spraying_fungicides3_spt3 cannot be null")
    private LocalDate penyemprotanFungisidaKetiga;

    @Column(nullable = false)
    @NotNull(message = "basic_fertilization cannot be null")
    private LocalDate pemupukanDasar;

    @Column(nullable = false)
    @NotNull(message = "fertilization1 cannot be null")
    private LocalDate pemupukanPertama;

    @Column(nullable = false)
    @NotNull(message = "fertilization2 cannot be null")
    private LocalDate pemupukanKedua;

    @Column(nullable = false)
    @NotNull(message = "spraying_insecticide2_double_fruit2 cannot be null")
    private LocalDate penyemprotanInsektisidaGandaBuah;

    @Column(nullable = false)
    @NotNull(message = "harvest cannot be null")
    private LocalDate panen;

}
