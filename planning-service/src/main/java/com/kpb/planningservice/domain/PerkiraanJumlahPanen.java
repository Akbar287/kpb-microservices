package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "perkiraan_jumlah_panen")
public class PerkiraanJumlahPanen implements Serializable {
    @Serial
    private static final long serialVersionUID = -6292545326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long perkiraanJumlahPanenId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jadwal_usaha_tani_id")
    private JadwalUsahaTani jadwalUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "harvested_dry_grain cannot be null")
    @NotEmpty(message = "harvested_dry_grain cannot be empty")
    private String gabahKeringPanen;

    @Column(nullable = false)
    @NotNull(message = "milled_dry_grain cannot be null")
    @NotEmpty(message = "milled_dry_grain cannot be empty")
    private String gabahKeringGiling;

    @Column(nullable = false)
    @NotNull(message = "rice cannot be null")
    private Integer beras;
}
