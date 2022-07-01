package com.kpb.creditservice.domain;

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
@Table(name = "business_credits")
public class BusinessCredit implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283184726252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long business_credit_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "businessCredit")
    private List<CreditStatus> creditStatusList;

    @Column(name = "farmer_id", nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    @NotEmpty(message = "farmer_id cannot be empty")
    private Long farmer_id;

    @Column(name = "necessary_name", nullable = false)
    @NotNull(message = "necessary_name cannot be null")
    @NotEmpty(message = "necessary_name cannot be empty")
    private int year;

    @Column(name = "amount", nullable = false)
    @NotNull(message = "amount cannot be null")
    @NotEmpty(message = "amount cannot be empty")
    private BigInteger amount;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
