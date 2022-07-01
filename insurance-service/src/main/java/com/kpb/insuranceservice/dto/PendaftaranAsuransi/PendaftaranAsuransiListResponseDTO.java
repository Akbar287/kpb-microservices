package com.kpb.insuranceservice.dto.PendaftaranAsuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PendaftaranAsuransiListResponseDTO {
    private Long pendaftaranAsuransiId;
    private String nomorAsuransi;
    private String waktuEfektif;
    private String waktuBerakhir;
    private String metodePembayaran;
    private BigInteger totalPembayaran;
}
