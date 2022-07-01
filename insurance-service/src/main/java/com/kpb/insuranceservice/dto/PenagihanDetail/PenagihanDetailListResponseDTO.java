package com.kpb.insuranceservice.dto.PenagihanDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanDetailListResponseDTO {
    private Long penagihanDetailId;
    private String tanggalBayar;
    private BigInteger jumlah;
}
