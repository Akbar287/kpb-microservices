package com.kpb.memberservice.dto.kios;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KiosCreateUpdateRequestDTO {
    private String nama;
    private String fileGambar;
    private String email;
    private String password;
    private String username;
    private String nomorTelepon;
    private String kodePihc;
    private String namaKomoditas;
    private Boolean verifikasiPasarBebas;
    private String alamat;
}
