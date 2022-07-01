package com.kpb.planningservice.dto.KekuranganPupuk;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KekuranganPupukResponseDTO {
    private Long kekuranganPupukId;
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    List<KekuranganPupukDetailDTO> details;
}
