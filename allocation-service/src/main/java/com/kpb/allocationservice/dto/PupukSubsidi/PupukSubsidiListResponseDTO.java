package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PupukSubsidiListResponseDTO {
    private Long pupukSubsidiId;
    private String nama;
    private String nik;
    private int tahun;
    private int masaTanam;
    private boolean isDigunakan;
}
