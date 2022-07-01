package com.kpb.insuranceservice.dto.DinasPopt;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DinasPoptListResponseDTO {
    private Long dinasPoptId;
    private String nama;
    private String alamat;
    private String namaKetua;
    private boolean isRegister;
}
