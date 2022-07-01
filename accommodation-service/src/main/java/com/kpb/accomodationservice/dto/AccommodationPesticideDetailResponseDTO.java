package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.accomodationservice.domain.*;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationPesticideDetailResponseDTO {
    private Long accommodationPesticideId;
    private String area;
    private String bugType;
    private String commodity;
    private String pesticideType;
    private String location;
    private String description;
    private int year;
    private int plantingTime;
    private Long farmerId;

    private PoptRecommendation poptRecommendation;
    private AccommodationPesticideConfirmation accommodationPesticideConfirmation;
    private List<AccommodationPesticideArea> accommodationPesticideAreas;
    private List<AccommodationPesticideFileRegistration> accommodationPesticideFileRegistrations;
    private List<AccommodationPesticideStatus> accommodationPesticideStatus;
}
