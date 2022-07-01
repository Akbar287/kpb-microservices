package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "hasil_pasca_panen")
public class HasilPascaPanen implements Serializable {
    @Serial
    private static final long serialVersionUID = -6277655326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hasilPascaPanenId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jadwal_usaha_tani_id")
    private JadwalUsahaTani jadwalUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "harvest_result cannot be null")
    @NotEmpty(message = "harvest_result cannot be empty")
    private String hasilPanen;

    private String deskripsi;

    @Column(nullable = false)
    private BigInteger pendapatanKotor;
}
