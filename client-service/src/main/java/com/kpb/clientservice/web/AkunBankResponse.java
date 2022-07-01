package com.kpb.clientservice.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AkunBankResponse {
    private Long akunBankId;
    private String nomorRekening;
    private String namaBank;
    private String kodeBank;
}
