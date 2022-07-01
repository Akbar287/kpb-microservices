package com.kpb.accomodationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "accommodation_area")
@Data
public class AccommodationArea implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155985452559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodation_area_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="accommodation_id")
    private Accommodation accommodation;

    @Column(name = "city_code", nullable = false)
    @NotNull(message = "city_code cannot be null")
    @NotEmpty(message = "city_code cannot be empty")
    private String city_code;

    @Column(name = "province_code", nullable = false)
    @NotNull(message = "province_code cannot be null")
    @NotEmpty(message = "province_code cannot be empty")
    private String province_code;

    @Column(name = "district_code", nullable = false)
    @NotNull(message = "district_code cannot be null")
    @NotEmpty(message = "district_code cannot be empty")
    private String district_code;

    @Column(name = "sub_district_code", nullable = false)
    @NotNull(message = "sub_district_code cannot be null")
    @NotEmpty(message = "sub_district_code cannot be empty")
    private String sub_district_code;

    @Column(name = "sub_district", nullable = false)
    @NotNull(message = "sub_district cannot be null")
    @NotEmpty(message = "sub_district cannot be empty")
    private String sub_district;

    @Column(name = "district", nullable = false)
    @NotNull(message = "district cannot be null")
    @NotEmpty(message = "district cannot be empty")
    private String district;

    @Column(name = "city", nullable = false)
    @NotNull(message = "city cannot be null")
    @NotEmpty(message = "city cannot be empty")
    private String city;

    @Column(name = "province", nullable = false)
    @NotNull(message = "province cannot be null")
    @NotEmpty(message = "province cannot be empty")
    private String province;
}
