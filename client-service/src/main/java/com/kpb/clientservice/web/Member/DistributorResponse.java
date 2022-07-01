package com.kpb.clientservice.web.Member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorResponse {
    private Long distributorId;
    private String nama;
    private String email;
    private String username;
    private String nomorTelepon;
    private String fileGambar;
    private String alamat;
    private String nomorAhu;
    private String jenisDistributor;
    private int status;
    private String createdAt;
    private String updatedAt;
}
