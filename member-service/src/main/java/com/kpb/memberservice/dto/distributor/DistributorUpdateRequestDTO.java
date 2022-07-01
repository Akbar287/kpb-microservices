package com.kpb.memberservice.dto.distributor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorUpdateRequestDTO {
    private String nama;
    private String username;
    private String fileGambar;
    private String nomorTelepon;
    private String email;
    private String alamat;
    private String nomorAhu;
    private String jenisDistributor;
}
