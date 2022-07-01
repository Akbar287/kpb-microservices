package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationRegistrationListResponse {
    private Long accommodationRegistrationId;
    private String commodity;
    private int plantingTime;
    private int year;
    private LocalDate createdAt;

    private Long accommodationId;
    private String accommodationName;

    private String statusLabel;
    private String statusValue;
}
