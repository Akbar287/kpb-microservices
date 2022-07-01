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
@Table(name = "accommodation_pesticide_registrations")
public class AccommodationPesticideRegistration implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283943326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_pesticide_registration_id;

    @OneToMany(mappedBy = "accommodationPesticideRegistration")
    private List<AccommodationPesticideArea> accommodationPesticideAreaList;

    @OneToOne(mappedBy = "accommodationPesticideRegistration")
    private PoptRecommendation poptRecommendations;

    @OneToMany(mappedBy = "accommodationPesticideRegistration")
    private List<AccommodationPesticideFileRegistration> accommodationPesticideFileRegistrationList;

    @OneToMany(mappedBy = "accommodationPesticideRegistration")
    private List<AccommodationPesticideStatus> accommodationPesticideStatusList;

    @OneToOne(mappedBy = "accommodationPesticideRegistration")
    private AccommodationPesticideConfirmation accommodationPesticideConfirmation;

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

    @Column(name = "location", nullable = false)
    @NotNull(message = "location cannot be null")
    @NotEmpty(message = "location cannot be empty")
    private String location;

    @Column(name = "area", nullable = false)
    @NotNull(message = "area cannot be null")
    @NotEmpty(message = "area cannot be empty")
    private String area;

    @Column(name = "pesticide_type", nullable = false)
    @NotNull(message = "pesticide_type cannot be null")
    @NotEmpty(message = "pesticide_type cannot be empty")
    private String pesticide_type;

    @Column(name = "bug_type", nullable = false)
    @NotNull(message = "bug_type cannot be null")
    @NotEmpty(message = "bug_type cannot be empty")
    private String bug_type;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "expired_at", nullable = false)
    private LocalDate expired_at;
}
