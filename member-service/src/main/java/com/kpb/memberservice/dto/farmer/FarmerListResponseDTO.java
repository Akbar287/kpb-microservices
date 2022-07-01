package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerListResponseDTO
{
    private Long petaniId;
    private String nik;
    private String nama;
    private String namaDesa;
    private String nomorTelepon;
    private String ibuKandung;
    private String statusPetani;
}
