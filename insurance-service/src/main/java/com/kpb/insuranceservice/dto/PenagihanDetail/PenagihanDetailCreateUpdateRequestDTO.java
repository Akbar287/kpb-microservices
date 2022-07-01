package com.kpb.insuranceservice.dto.PenagihanDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanDetailCreateUpdateRequestDTO {
    private Long penagihanId;
    private int tanggalBayar;
    private BigInteger jumlah;
    private String metodePembayaran;
    private String namaFile;
    private String namaDokumen;
    private String informasi;
}
