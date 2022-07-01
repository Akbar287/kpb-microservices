package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.accomodationservice.domain.AccommodationArea;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationDetailResponseDTO {
    private Long accommodationId;
    private String accommodationName;
    private String accommodationType;
    private int accommodationNumber;
    private String description;
    private String unit;

    private List<AccommodationArea> accommodationAreas;

    private int registration;
}
