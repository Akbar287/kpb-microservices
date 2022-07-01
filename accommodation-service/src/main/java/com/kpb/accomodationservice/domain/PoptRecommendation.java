package com.kpb.accomodationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "popt_recommendations")
public class PoptRecommendation implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326264289809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long popt_recommendation_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_pesticide_registration_id")
    private AccommodationPesticideRegistration accommodationPesticideRegistration;

    @Column(name = "popt_officer_id", nullable = false)
    @NotNull(message = "popt officer cannot be null")
    @NotEmpty(message = "popt officer cannot be empty")
    private Long popt_officer_id;

    @Column(name = "statement_letter_number", nullable = false)
    @NotNull(message = "statement letter number cannot be null")
    @NotEmpty(message = "statement letter number cannot be empty")
    private String statement_letter_number;

    @Column(name = "statement_letter_date", nullable = false)
    @NotNull(message = "statement letter date cannot be null")
    @NotEmpty(message = "statement letter date cannot be empty")
    private LocalDate statement_letter_date;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String file_name;

    @Column(name = "file_document", nullable = false)
    @NotNull(message = "file document cannot be null")
    @NotEmpty(message = "file document cannot be empty")
    private String file_document;

    @Column(name = "description")
    private String description;

}
