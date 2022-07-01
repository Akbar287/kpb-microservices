package com.kpb.memberservice.dto.farmerGroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerGroupListResponseDTO {
    private Long poktanId;
    private Long userId;
    private String namaPoktan;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private boolean statusKonfirmasi;
}
