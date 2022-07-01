package com.kpb.clientservice.web.Member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PabrikanResponse {
    private Long pabrikanId;
    private String nama;
    private String fileGambar;
    private String email;
    private String username;
    private String nomorTelepon;
    private String jenisPabrik;
    private String alamat;
    private String createdAt;
    private String updatedAt;
}
