package com.kpb.insuranceservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "penagihan_detail_bank")
public class PenagihanBankDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155355362559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penagihanDetailBankId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "penagihan_detail_id")
    private PenagihanDetail penagihanDetail;

    @Column(nullable = false)
    @NotNull(message = "payer first name cannot be null")
    @NotEmpty(message = "payer first name cannot be empty")
    private String pembayarNamaPertama;

    @Column(nullable = false)
    @NotNull(message = "payer last name cannot be null")
    @NotEmpty(message = "payer last name cannot be empty")
    private String pembayarNamaTerakhir;

    @Column(nullable = false)
    @NotNull(message = "card number cannot be null")
    @NotEmpty(message = "card number cannot be empty")
    private String nomorKartu;

    @Column(nullable = false)
    @NotNull(message = "bank name cannot be null")
    @NotEmpty(message = "bank name cannot be empty")
    private String namaBank;

    @Column(nullable = false)
    @NotNull(message = "account number cannot be null")
    @NotEmpty(message = "account number cannot be empty")
    private String nomorRekening;

    @Column(nullable = false)
    @NotNull(message = "card type cannot be null")
    @NotEmpty(message = "card type cannot be empty")
    private String jenisKartu;

    @Column(nullable = false)
    @NotNull(message = "card expired date cannot be null")
    @NotEmpty(message = "card expired date cannot be empty")
    private LocalDate tanggalExpireKartu;
}
