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
@Table(name = "accommodation_confirmation")
public class AccommodationConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_confirmation_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accommodation_registration_id")
    private AccommodationRegistration accommodationRegistration;

    @Column(name = "confirmation_by", nullable = false)
    @NotNull(message = "confirmation_by cannot be null")
    @NotEmpty(message = "confirmation_by cannot be empty")
    private String confirmation_by;

    @Column(name = "confirmation_type", nullable = false)
    @NotNull(message = "confirmation_type cannot be null")
    @NotEmpty(message = "confirmation_type cannot be empty")
    private String confirmation_type;

    @Column(name = "confirmation_date", nullable = false)
    @NotNull(message = "confirmation_date cannot be null")
    @NotEmpty(message = "confirmation_date cannot be empty")
    private LocalDate confirmation_date;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
