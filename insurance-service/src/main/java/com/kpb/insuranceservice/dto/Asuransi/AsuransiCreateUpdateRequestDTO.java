package com.kpb.insuranceservice.dto.Asuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AsuransiCreateUpdateRequestDTO {
    private Long pelaksanaAsuransiUserId;
    private String namaAsuransi;
    private String jenisAsuransi;
    private int biaya;
    private String informasi;
    private boolean isPublished;
}
