package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "award_criterias")
public class AwardCriteria implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155385322376109L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long award_criteria_id;

    @Column(name = "award_criteria_description", nullable = false)
    @NotNull(message = "award criteria description cannot be null")
    @NotEmpty(message = "award criteria description cannot be empty")
    private String award_criteria_description;
}
