package com.kpb.memberservice.dto.penyuluh;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.ArrayList;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenyuluhCreateUpdateRequestDTO {
    private String nama;
    private String fileGambar;
    private String email;
    private String password;
    private String username;
    private String nomorTelepon;
    private String alamat;
    private boolean verifikasi;
    private ArrayList kios;
}
