package com.kpb.planningservice.dto.RealisasiPupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RealisasiPupukSubsidiCreateUpdateRequestDTO {
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    List<RealisasiPupukSubsidiDetailDTO> details;
}
