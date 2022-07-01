package com.kpb.insuranceservice.dto.PenagihanDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.Penagihan;
import com.kpb.insuranceservice.domain.PenagihanBankDetail;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanDetailDetailResponseDTO {
    private Long penagihanDetailId;
    private String tanggalBayar;
    private Integer jumlah;
    private String metodePembayaran;
    private String namaFile;
    private String namaDokumen;
    private String informasi;
    private String createdAt;
    private String updatedAt;

    private Penagihan penagihan;
    private PenagihanBankDetail penagihanBankDetail;
}
