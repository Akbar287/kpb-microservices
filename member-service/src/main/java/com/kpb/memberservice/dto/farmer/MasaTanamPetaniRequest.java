package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MasaTanamPetaniRequest {
    private String jumlah;
    private int masaTanam;
    private String namaKomoditas;
    private String subsektor;
}
