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
@Table(name = "harga")
public class Harga implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752595909L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hargaId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produk_id")
    private Produk produk;

    @Column(nullable = false)
    @NotNull(message = "price cannot be null")
    private BigInteger harga;

    @Column(nullable = false)
    @NotNull(message = "price_stand cannot be null")
    private BigInteger hargaKios;

    @Column(nullable = false)
    @NotNull(message = "price_subsidy cannot be null")
    private BigInteger hargaSubsidi;

    @Column(nullable = false)
    @NotNull(message = "price_stand_subsidy cannot be null")
    private BigInteger hargaKiosSubsidi;

    @Column(nullable = false)
    @NotNull(message = "fee_stand cannot be null")
    private BigInteger biayaKios;

    @Column(nullable = false)
    @NotNull(message = "fee_bita cannot be null")
    private BigInteger biayaLain;

    @Column(nullable = false)
    @NotNull(message = "fee_stand_subsidy cannot be null")
    private BigInteger biayaKiosSubsidi;
}
