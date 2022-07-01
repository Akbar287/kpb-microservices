package com.kpb.planningservice.dto.RencanaUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RencanaUsahaTaniCreateUpdateRequestDTO {
    private Long userId;
    private Long petaniId;
    private Integer tahun;
    private Integer luasLahan;
    private String status;
    private Integer totalBiayaUsahaTani;
    private Integer grandTotal;
    private Integer prediksiPendapatan;
    private String description;
}
