package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationListResponseDTO {
    private Long accommodationId;
    private String accommodationName;
    private String accommodationType;
    private int accommodationNumber;
    private String unit;
    private LocalDate createdAt;
}
