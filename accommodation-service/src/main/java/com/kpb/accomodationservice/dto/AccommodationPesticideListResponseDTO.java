package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationPesticideListResponseDTO {
    private Long accommodationPesticideId;
    private String bugType;
    private String commodity;
    private String pesticideType;
    private String location;
    private int year;
    private int plantingTime;
    private int fileUploaded;
    private Boolean recommendation;
    private Boolean confirmation;
}
