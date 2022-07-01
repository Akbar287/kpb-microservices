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
@Table(name = "scholarship_types")
public class ScholarshipType implements Serializable {

    @Serial
    private static final long serialVersionUID = -6282465325753269809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scholarship_type_id;

    @OneToMany(mappedBy = "scholarshipType")
    private List<Scholarships> scholarships;

    @Column(name = "spending_criteria", nullable = false)
    @NotNull(message = "spending criteria cannot be null")
    @NotEmpty(message = "spending criteria cannot be empty")
    private String scholarship_type_description;

}
