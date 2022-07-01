package com.kpb.planningservice.dto.RealisasiPupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RealisasiPupukSubsidiDetailDTO {
    private Long realisasiPupukSubsidiDetailId;
    private String namaPupuk;
    private Integer jumlah;
}
