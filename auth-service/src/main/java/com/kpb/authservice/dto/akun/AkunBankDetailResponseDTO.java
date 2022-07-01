package com.kpb.authservice.dto.akun;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AkunBankDetailResponseDTO {
    private Long akunBankId;
    private String nomorRekening;
    private String namaBank;
    private String kodeBank;
}
