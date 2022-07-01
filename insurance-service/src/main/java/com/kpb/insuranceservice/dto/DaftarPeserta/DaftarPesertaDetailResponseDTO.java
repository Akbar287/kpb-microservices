package com.kpb.insuranceservice.dto.DaftarPeserta;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DaftarPesertaDetailResponseDTO {
    private Long daftarPesertaId;
    private int tahun;
    private int masaTanam;
    private String namaFile;
    private String namaDokumen;
    private int keterangan;
}
