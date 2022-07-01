package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "scholarship_rounds")
public class ScholarshipRound implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155318552559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scholarship_round_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scholarship_id")
    private Scholarships scholarships;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(name = "quarter_paid_out", nullable = false)
    @NotNull(message = "quarter paid out cannot be null")
    @NotEmpty(message = "quarter paid out cannot be empty")
    private String quarter_paid_out;

    @Column(name = "number_applicants", nullable = false)
    @NotNull(message = "number applicants cannot be null")
    @NotEmpty(message = "number applicants cannot be empty")
    private int number_applicants;

    @Column(name = "number_awards", nullable = false)
    @NotNull(message = "number awards cannot be null")
    @NotEmpty(message = "number awards cannot be empty")
    private int number_awards;

    @Column(name = "total_amount_award", nullable = false)
    @NotNull(message = "total amount award cannot be null")
    @NotEmpty(message = "total amount award cannot be empty")
    private BigInteger total_amount_award;

}
