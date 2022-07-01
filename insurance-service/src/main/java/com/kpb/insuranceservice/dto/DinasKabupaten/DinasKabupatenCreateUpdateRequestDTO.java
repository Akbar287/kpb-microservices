package com.kpb.insuranceservice.dto.DinasKabupaten;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DinasKabupatenCreateUpdateRequestDTO {
    private String nama;
    private String email;
    private String username;
    private String password;
    private String nomorTelepon;
    private String fileGambar;
    private String alamat;
    private String namaKetua;
    private String namaKabupaten;
    private Integer kodeKabupaten;
    private boolean isRegister;
    private boolean isKota;

}
