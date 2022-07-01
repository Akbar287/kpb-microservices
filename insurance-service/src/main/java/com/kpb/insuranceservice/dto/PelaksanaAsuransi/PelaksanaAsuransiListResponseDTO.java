package com.kpb.insuranceservice.dto.PelaksanaAsuransi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PelaksanaAsuransiListResponseDTO {
    private Long pelaksanaAsuransiId;
    private String nama;
    private String namaKetua;
    private String email;
    private String alamat;
    private String jenisAsuransi;
}
