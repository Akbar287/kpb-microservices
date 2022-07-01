package com.kpb.creditservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "credit_status")
public class CreditStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252845809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credit_status_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_credit_id")
    private BusinessCredit businessCredit;

    @Column(name = "label", nullable = false)
    @NotNull(message = "label cannot be null")
    @NotEmpty(message = "label cannot be empty")
    private String label;

    @Column(name = "value", nullable = false)
    @NotNull(message = "value cannot be null")
    @NotEmpty(message = "value cannot be empty")
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
