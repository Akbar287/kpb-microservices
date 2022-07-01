package com.kpb.accomodationservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccommodationPoptRecommendationDTO {
    private String fileName;
    private String fileDocument;
    private String description;
    private Long PoptOfficer;
    private LocalDate statementLetterDate;
    private String StatementLetterNumber;

}
