package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Scholarship_contact_informations")
public class ScholarshipContactInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155323742559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Scholarship_contact_information_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scholarship_id")
    private Scholarships scholarships;

    @Column(name = "contact_first_name", nullable = false)
    @NotNull(message = "contact first name cannot be null")
    @NotEmpty(message = "contact first name cannot be empty")
    private String contact_first_name;

    @Column(name = "contact_last_name", nullable = false)
    @NotNull(message = "contact last name cannot be null")
    @NotEmpty(message = "contact last name cannot be empty")
    private String contact_last_name;

    @Column(name = "contact_address", nullable = false)
    @NotNull(message = "contact address cannot be null")
    @NotEmpty(message = "contact address cannot be empty")
    private String contact_address;

    @Column(name = "contact_city", nullable = false)
    @NotNull(message = "contact city cannot be null")
    @NotEmpty(message = "contact city cannot be empty")
    private String contact_city;

    @Column(name = "contact_state", nullable = false)
    @NotNull(message = "contact state cannot be null")
    @NotEmpty(message = "contact state cannot be empty")
    private String contact_state;

    @Column(name = "contact_country", nullable = false)
    @NotNull(message = "contact country cannot be null")
    @NotEmpty(message = "contact country cannot be empty")
    private String contact_country;

    @Column(name = "contact_zip_code", nullable = false)
    @NotNull(message = "contact zip code cannot be null")
    @NotEmpty(message = "contact zip code cannot be empty")
    private String contact_zip_code;

    @Column(name = "contact_phone", nullable = false)
    @NotNull(message = "contact phone cannot be null")
    @NotEmpty(message = "contact phone cannot be empty")
    private String contact_phone;

    @Column(name = "contact_email", nullable = false)
    @NotNull(message = "contact email cannot be null")
    @NotEmpty(message = "contact email cannot be empty")
    private String contact_email;

    @Column(name = "contact_note", nullable = false)
    @NotNull(message = "contact note cannot be null")
    @NotEmpty(message = "contact note cannot be empty")
    private String contact_note;

}
