package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationRegistrationCreateUpdateRequestDTO {
    private Long accommodationId;
    private Long farmerId;
    private int plantingTime;
    private int year;
    private String fileName;
    private String fileProposal;
    private String commodity;
    private String description;
}
