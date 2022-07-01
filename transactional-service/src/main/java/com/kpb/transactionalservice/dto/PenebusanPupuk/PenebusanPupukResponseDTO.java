package com.kpb.transactionalservice.dto.PenebusanPupuk;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenebusanPupukResponseDTO {
    private Long penebusanPupukId;
    private boolean isPetani;
    private boolean isPoktan;
}
