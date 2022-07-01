package com.kpb.insuranceservice.dto.Penagihan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanListResponseDTO {
    private Long penagihanId;
    private String batasWaktu;
    private String status;
}
