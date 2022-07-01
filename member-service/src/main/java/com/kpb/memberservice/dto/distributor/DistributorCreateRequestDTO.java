package com.kpb.memberservice.dto.distributor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorCreateRequestDTO {
    private String nama;
    private String username;
    private String email;
    private String password;
    private String fileGambar;
    private String alamat;
    private String nomorAhu;
    private String nomorTelepon;
    private String jenisDistributor;
}
