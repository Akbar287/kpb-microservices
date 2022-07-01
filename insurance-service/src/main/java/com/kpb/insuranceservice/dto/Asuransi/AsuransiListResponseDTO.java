package com.kpb.insuranceservice.dto.Asuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AsuransiListResponseDTO {
    private Long asuransiId;
    private String namaAsuransi;
    private String jenisAsuransi;
    private String biaya;
}
