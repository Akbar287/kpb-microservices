package com.kpb.planningservice.dto.JenisTanaman;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JenisTanamanResponseDTO {
    private Long jenisTanamanId;
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    private String namaTanaman;
}
