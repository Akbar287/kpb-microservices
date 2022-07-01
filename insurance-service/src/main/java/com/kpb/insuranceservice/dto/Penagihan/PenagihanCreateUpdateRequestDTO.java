package com.kpb.insuranceservice.dto.Penagihan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanCreateUpdateRequestDTO {
    private Long pendaftaranAsuransiId;
    private LocalDateTime batasWaktu;
    private BigInteger minimalPembayaran;
    private BigInteger saldo;
    private String status;
}
