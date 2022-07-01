package com.kpb.insuranceservice.dto.Asuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.PelaksanaAsuransi;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AsuransiDetailResponseDTO {
    private Long asuransiId;
    private String namaAsuransi;
    private String jenisAsuransi;
    private String biaya;
    private String informasi;
    private boolean isPublished;

    private PelaksanaAsuransi pelaksanaAsuransi;
}
