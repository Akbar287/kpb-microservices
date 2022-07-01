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

@Entity
@Data
@Table(name = "penagihan_detail")
public class PenagihanDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283152225752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penagihanDetailId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name ="penagihan_id")
    private Penagihan penagihan;

    @JsonIgnore
    @OneToOne(mappedBy = "penagihanDetail")
    private PenagihanBankDetail penagihanBankDetail;

    @Column( nullable = false)
    @NotNull(message = "paid date cannot be null")
    @NotEmpty(message = "paid date cannot be empty")
    private LocalDate tanggalBayar;

    @Column( nullable = false)
    @NotNull(message = "amount cannot be null")
    @NotEmpty(message = "amount cannot be empty")
    private BigInteger jumlah;

    @Column( nullable = false)
    @NotNull(message = "payment method cannot be null")
    @NotEmpty(message = "payment method cannot be empty")
    private String metodePembayaran;

    private String namaFile;

    private String namaDokumen;

    private String informasi;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;
}
