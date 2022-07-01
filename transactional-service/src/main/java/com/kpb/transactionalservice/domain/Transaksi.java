package com.kpb.transactionalservice.domain;

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
@Table(name = "transaksi")
public class Transaksi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155398782573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaksiId;

    @JsonIgnore
    @OneToMany(mappedBy = "transaksi")
    private List<TransaksiDetail> transaksiDetail;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "penebusan_pupuk_id")
    private PenebusanPupuk penebusanPupuk;

    @Column( nullable = false)
    @NotNull(message = "petani cannot be null")
    private Long petaniId;

    @Column( nullable = false)
    @NotNull(message = "transaction_name cannot be null")
    @NotEmpty(message = "transaction_name cannot be empty")
    private String namaTransaksi;

    @Column( nullable = false)
    @NotNull(message = "grand_total cannot be null")
    private BigInteger grandTotal;

    @Column( nullable = false)
    @NotNull(message = "payment_method cannot be null")
    @NotEmpty(message = "payment_method cannot be empty")
    private String metodePembayaran;

    private Long kiosId;

    private String virtualAccount;

    @Column( nullable = false)
    private boolean statusKredit;

    @Column( nullable = false)
    private boolean verifikasiKredit;

    private BigInteger statusRegistrasiKredit;

    @Column( nullable = false)
    private boolean isTransferKios;

    @Column( nullable = false)
    private boolean isPenebusan;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;
}
