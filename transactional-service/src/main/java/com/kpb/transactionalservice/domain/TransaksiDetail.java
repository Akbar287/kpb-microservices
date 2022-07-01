package com.kpb.transactionalservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "transaksi_detail")
public class TransaksiDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752578871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaksiDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produk_id")
    private Produk produk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaksi_id")
    private Transaksi transaksi;

    @Column(nullable = false)
    private int tahun;

    @Column(nullable = false)
    private int masaTanam;

    @Column(nullable = false)
    @NotNull(message = "commodity cannot be null")
    @NotEmpty(message = "commodity cannot be empty")
    private String komoditas;

    @Column(nullable = false)
    @NotNull(message = "type cannot be null")
    @NotEmpty(message = "type cannot be empty")
    private String jenis;

    @Column(nullable = false)
    private int jumlah;

    @Column(nullable = false)
    private BigInteger harga;

    @Column(nullable = false)
    private boolean subsidi;

    @Column(nullable = false)
    private BigInteger total;
}
