package com.kpb.memberservice.dto.farmerGroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerGroupAddFarmerRequestDTO {
    private String nik;
    private String nama;
    private String ibuKandung;
    private String jenisKelamin;
    private String statusPetani;
    private String nomorTelepon;
    private String email;
    private String username;
    private String alamat;
}
