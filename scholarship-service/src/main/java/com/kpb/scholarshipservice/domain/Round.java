package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "rounds")
public class Round implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long round_id;

    @OneToMany(mappedBy = "round")
    private List<ScholarshipRound> scholarshipRoundList;

    @Column(name = "round_name", nullable = false)
    @NotNull(message = "round name cannot be null")
    @NotEmpty(message = "round name cannot be empty")
    private String round_name;

    @Column(name = "calendar_year", nullable = false)
    @NotNull(message = "calendar year cannot be null")
    @NotEmpty(message = "calendar year cannot be empty")
    private int calendar_year;

    @Column(name = "fiscal_year", nullable = false)
    @NotNull(message = "fiscal year cannot be null")
    @NotEmpty(message = "fiscal year cannot be empty")
    private int fiscal_year;

    @Column(name = "fiscal_year_description", nullable = true)
    private String fiscal_year_description;
}
