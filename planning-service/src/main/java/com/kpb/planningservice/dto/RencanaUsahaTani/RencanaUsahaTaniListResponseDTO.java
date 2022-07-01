package com.kpb.planningservice.dto.RencanaUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RencanaUsahaTaniListResponseDTO {
    private Long RencanaUsahaTaniId;
    private String nik;
    private String petani;
    private Integer tahun;
    private Integer luasLahan;
}
