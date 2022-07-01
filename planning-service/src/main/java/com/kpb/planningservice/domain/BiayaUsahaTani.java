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
@Table(name = "biaya_usaha_tani")
public class BiayaUsahaTani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283198526252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biayaUsahaTaniId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column(nullable = false)
    private String jenisBiayaUsahaTani;

    @Column(nullable = false)
    private String jenis;

    @Column(nullable = false)
    private BigInteger harga;

    @Column(nullable = false)
    @NotNull(message = "quantity cannot be null")
    private Integer kuantitas;

    @Column(nullable = false)
    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be empty")
    private String satuan;
}
