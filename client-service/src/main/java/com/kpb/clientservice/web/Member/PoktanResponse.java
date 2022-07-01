package com.kpb.clientservice.web.Member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PoktanResponse {
    private Long poktanId;
    private Long userId;
    private String namaPoktan;
    private String email;
    private String usernamePoktan;
    private String nomorTelepon;
    private String fileGambar;
    private String jenisPoktan;
    private String namaKetuaPoktan;
    private boolean registrasi;
    private boolean statusKonfirmasi;
    private String alamat;
    private String createdAt;
    private String updatedAt;
}
