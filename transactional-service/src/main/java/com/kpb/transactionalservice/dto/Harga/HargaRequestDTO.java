package com.kpb.transactionalservice.dto.Harga;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HargaRequestDTO {
    private Long produkId;
    private BigInteger harga;
    private BigInteger hargaKios;
    private BigInteger hargaSubsidi;
    private BigInteger hargaKiosSubsidi;
    private BigInteger biayaKios;
    private BigInteger biayaLain;
    private BigInteger biayaKiosSubsidi;
}
