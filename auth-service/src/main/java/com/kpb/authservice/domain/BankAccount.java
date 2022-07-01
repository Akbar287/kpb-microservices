package com.kpb.authservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "akun_bank")
public class BankAccount implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325716559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long akunBankId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 32)
    @NotNull(message = "account number cannot be null")
    @NotEmpty(message = "account number cannot be empty")
    private String nomorRekening;

    @Column(nullable = false, length = 64)
    @NotNull(message = "bank name cannot be null")
    @NotEmpty(message = "bank name cannot be empty")
    private String namaBank;

    @Column(nullable = false, length = 2)
    @NotNull(message = "bank code cannot be null")
    @NotEmpty(message = "bank code cannot be empty")
    private String kodeBank;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
