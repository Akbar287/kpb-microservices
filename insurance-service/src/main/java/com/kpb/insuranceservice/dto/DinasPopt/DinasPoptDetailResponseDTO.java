package com.kpb.insuranceservice.dto.DinasPopt;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DinasPoptDetailResponseDTO {
    private Long dinasPoptId;
    private String nama;
    private String email;
    private String username;
    private String nomorTelepon;
    private String fileGambar;
    private String alamat;
    private String namaKetua;
    private boolean isRegister;
}
