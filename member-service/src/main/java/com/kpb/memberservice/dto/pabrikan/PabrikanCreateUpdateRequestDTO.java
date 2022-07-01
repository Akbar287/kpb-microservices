package com.kpb.memberservice.dto.pabrikan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PabrikanCreateUpdateRequestDTO {
    private String nama;
    private String fileGambar;
    private String email;
    private String password;
    private String username;
    private String nomorTelepon;
    private String jenisPabrik;
    private String alamat;
}
