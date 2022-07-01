package com.kpb.insuranceservice.dto.Klaim;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KlaimListResponseDTO {
    private Long klaimId;
    private String namaKlaim;
    private String kelompokKlaim;
    private int kode;
}
