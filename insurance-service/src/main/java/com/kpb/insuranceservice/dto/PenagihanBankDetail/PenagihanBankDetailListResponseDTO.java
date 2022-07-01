package com.kpb.insuranceservice.dto.PenagihanBankDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanBankDetailListResponseDTO {
    private Long penagihanBankDetailId;
    private String pembayarNamaPertama;
    private String pembayarNamaTerakhir;
    private String nomorKartu;
    private String namaBank;
    private String nomorRekening;
}
