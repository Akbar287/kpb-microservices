package com.kpb.marketservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "bank_accounts")
public class BankAcccount implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155557852559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bank_account_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "buyer_account_bank", joinColumns = @JoinColumn(name = "bank_account_id"), inverseJoinColumns = @JoinColumn(name = "buyer_id"))
    private Buyer buyer;

    @Column(name = "bank_id", nullable = false)
    @NotNull(message = "bank_id cannot be null")
    @NotEmpty(message = "bank_id cannot be empty")
    private Long bank_id;

    @Column(name = "account_number", nullable = false)
    @NotNull(message = "account_number cannot be null")
    @NotEmpty(message = "account_number cannot be empty")
    private String account_number;

    @Column(name = "bank_name", nullable = false)
    @NotNull(message = "bank_name cannot be null")
    @NotEmpty(message = "bank_name cannot be empty")
    private String bank_name;

    @Column(name = "bank_code", nullable = false)
    @NotNull(message = "bank_code cannot be null")
    @NotEmpty(message = "bank_code cannot be empty")
    private String bank_code;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;
}
