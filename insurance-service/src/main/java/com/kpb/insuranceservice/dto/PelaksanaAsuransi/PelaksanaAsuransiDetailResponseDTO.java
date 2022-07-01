package com.kpb.insuranceservice.dto.PelaksanaAsuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PelaksanaAsuransiDetailResponseDTO {
    private Long pelaksanaAsuransiId;
    private String nama;
    private String namaKetua;
    private String email;
    private String username;
    private String nomorTelepon;
    private String fileGambar;
    private String alamat;
    private String jenisAsuransi;
    private String keterangan;
}
