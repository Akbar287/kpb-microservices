package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.memberservice.domain.MasaTanamPetani;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerAssetCreateUpdateRequestDTO {
    private String jenisAset;
    private String namaAset;
    private int totalAset;
    private List<MasaTanamPetaniRequest> masaTanamPetani;
}
