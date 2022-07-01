package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationPesticideConfirmationRequestDTO {
    private String confirmationBy;
    private String confirmationType;
    private String description;
}
