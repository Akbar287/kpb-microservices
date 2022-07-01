package com.kpb.insuranceservice.dto.DinasKabupaten;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DinasKabupatenListResponseDTO {
    private Long dinasKabupatenId;
    private String nama;
    private String email;
    private String namaKabupaten;
    private boolean isKota;
}
