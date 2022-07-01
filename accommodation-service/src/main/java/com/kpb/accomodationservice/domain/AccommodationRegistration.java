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
@Table(name = "accomodation_registrations")
public class AccommodationRegistration implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_registration_id;

    @OneToMany(mappedBy = "accommodationRegistration")
    private List<AccommodationStatus> accommodationStatusList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @OneToOne(mappedBy = "accommodationRegistration")
    private AccommodationConfirmation accommodationConfirmation;

    @Column(name = "farmer_id", nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    @NotEmpty(message = "farmer_id cannot be empty")
    private Long farmer_id;

    @Column(name = "year", nullable = false)
    @NotNull(message = "year cannot be null")
    @NotEmpty(message = "year cannot be empty")
    private int year;

    @Column(name = "planting_time", nullable = false)
    @NotNull(message = "planting_time cannot be null")
    @NotEmpty(message = "planting_time cannot be empty")
    private int planting_time;

    @Column(name = "commodity", nullable = false)
    @NotNull(message = "commodity cannot be null")
    @NotEmpty(message = "commodity cannot be empty")
    private String commodity;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file_name cannot be null")
    @NotEmpty(message = "file_name cannot be empty")
    private String file_name;

    @Column(name = "file_proposal", nullable = false)
    @NotNull(message = "file_proposal cannot be null")
    @NotEmpty(message = "file_proposal cannot be empty")
    private String file_proposal;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
