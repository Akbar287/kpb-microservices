package com.kpb.insuranceservice.dto.UsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsahaTaniListResponseDTO {
    private Long usahaTaniId;
    private int tahun;
    private int masaTanam;
    private boolean isAktif;

    private String name;
    private String username;
}
