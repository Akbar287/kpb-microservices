package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "achievements")
public class Achievement implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievement_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "achievements_date", nullable = false)
    @NotNull(message = "achievements date cannot be null")
    @NotEmpty(message = "achievements date cannot be empty")
    private LocalDate achievements_date;

    @Column(name = "achievements_details", nullable = false)
    @NotNull(message = "achievements details cannot be null")
    @NotEmpty(message = "achievements details cannot be empty")
    private String achievements_details;

    @Column(name = "other_details")
    private String other_details;

}
