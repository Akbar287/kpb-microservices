package com.kpb.insuranceservice.dto.PendaftaranAsuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.Asuransi;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PendaftaranAsuransiDetailResponseDTO {
    private Long pendaftaranAsuransiId;
    private String nomorAsuransi;
    private int tahun;
    private int masaTanam;
    private String waktuEfektif;
    private String waktuBerakhir;
    private String metodePembayaran;
    private int totalPembayaran;
    private String namaFile;
    private String namaDokumen;
    private boolean isAktif;
    private String informasi;
    private Asuransi asuransi;
}
