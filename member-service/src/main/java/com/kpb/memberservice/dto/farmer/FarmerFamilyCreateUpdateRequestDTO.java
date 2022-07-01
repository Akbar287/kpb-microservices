package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerFamilyCreateUpdateRequestDTO {
    private String kepalaKeluarga;
    private List<TanggunganPetaniRequest> tanggunganPetani;
    private List<TanggunganLainRequest> tanggunganLain;
}
