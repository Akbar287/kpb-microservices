package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationPesticideRegistrationCreateUpdateDTO {
    private String area;
    private String bugType;
    private String commodity;
    private String pesticideType;
    private String location;
    private String description;
    private int year;
    private int plantingTime;
    private Long farmerId;

    private List<AccommodationPesticideAreaDTO> accommodationPesticideAreaDTOS;
    private List<AccommodationPesticideFileDTO> accommodationPesticideFileDTOList;
}
