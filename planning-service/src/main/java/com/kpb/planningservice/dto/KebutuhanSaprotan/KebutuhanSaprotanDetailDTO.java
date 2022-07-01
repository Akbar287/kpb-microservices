package com.kpb.planningservice.dto.KebutuhanSaprotan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KebutuhanSaprotanDetailDTO {
    private Long kebutuhanSaprotanDetailId;
    private String namaSaprotan;
    private Integer jumlah;
}
