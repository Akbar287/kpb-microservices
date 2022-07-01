package com.kpb.planningservice.dto.KebutuhanSaprotan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KebutuhanSaprotanRequestDTO {
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    private String jenisSaprotan;
    List<KebutuhanSaprotanDetailDTO> details;
}
