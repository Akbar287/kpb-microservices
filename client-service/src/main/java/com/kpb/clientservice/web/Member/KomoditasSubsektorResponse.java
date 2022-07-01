package com.kpb.clientservice.web.Member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KomoditasSubsektorResponse {
    private String namaKomoditas;
    private String subsektor;
}
