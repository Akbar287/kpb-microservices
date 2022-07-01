package com.kpb.transactionalservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "stok_kios")
public class Stok implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752642809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stokKiosId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produk_id")
    private Produk produk;

    @Column(nullable = false)
    @NotNull(message = "stand_id cannot be null")
    private Long kiosId;

    @Column(nullable = false)
    @NotNull(message = "distributor_id cannot be null")
    private Long distributorId;

    @Column(nullable = false)
    private int tahun;

    @Column(nullable = false)
    @NotNull(message = "month cannot be null")
    @NotEmpty(message = "month cannot be empty")
    private String bulan;

    @Column(nullable = false)
    private int stokAwal;

    @Column(nullable = false)
    private int stokSubsidi;

    @Column(nullable = false)
    private int stokPenyaluran;

    @Column(nullable = false)
    private int stokAkhir;

}
