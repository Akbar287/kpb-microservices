package com.kpb.allocationservice.dto.PupukSubsidiDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PupukSubsidiResponseDTO {
    private Long pupukSubsidiDetailId;
    private String namaPupuk;
    private int jumlah;
}
