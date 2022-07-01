package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.accomodationservice.domain.AccommodationConfirmation;
import com.kpb.accomodationservice.domain.AccommodationStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationRegistrationDetailResponseDTO {
    private Long accommodationRegistrationId;
    private String commodity;
    private String fileName;
    private String fileProposal;
    private String description;
    private int plantingTime;
    private int year;
    private LocalDate createdAt;

    private AccommodationConfirmation accommodationConfirmation;
    private List<AccommodationStatus> accommodationStatus;
}
