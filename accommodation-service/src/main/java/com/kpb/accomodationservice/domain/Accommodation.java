package com.kpb.accomodationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "accommodations")
public class Accommodation implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326243259809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_id;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationArea> accommodationAreaList;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationRegistration> accommodationRegistrationList;

    @Column(name = "accommodation_name", nullable = false)
    @NotNull(message = "accommodation name cannot be null")
    @NotEmpty(message = "accommodation name cannot be empty")
    private String accommodation_name;

    @Column(name = "accommodation_type", nullable = false)
    @NotNull(message = "accommodation type cannot be null")
    @NotEmpty(message = "accommodation type cannot be empty")
    private String accommodation_type;

    @Column(name = "accommodation_number", nullable = false)
    @NotNull(message = "accommodation number cannot be null")
    @NotEmpty(message = "accommodation number cannot be empty")
    private int accommodation_number;

    @Column(name = "unit", nullable = false)
    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be empty")
    private String unit;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "expired_at", nullable = false)
    private LocalDate expired_at;
}
