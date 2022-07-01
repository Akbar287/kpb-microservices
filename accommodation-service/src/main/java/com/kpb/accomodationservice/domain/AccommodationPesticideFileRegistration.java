package com.kpb.accomodationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "accommodation_pesticide_file_registrations")
public class AccommodationPesticideFileRegistration implements Serializable {
    @Serial
    private static final long serialVersionUID = -6282860326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_pesticide_file_registration_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_pesticide_registration_id")
    private AccommodationPesticideRegistration accommodationPesticideRegistration;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String file_name;

    @Column(name = "file_document", nullable = false)
    @NotNull(message = "file document cannot be null")
    @NotEmpty(message = "fil document cannot be empty")
    private String file_document;

    @Column(name = "description")
    private String description;
}
