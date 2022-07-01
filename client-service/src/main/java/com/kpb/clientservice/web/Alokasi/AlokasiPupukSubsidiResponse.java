package com.kpb.clientservice.web.Alokasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlokasiPupukSubsidiResponse {
    private Long alokasiPupukSubsidiId;
    private String namaPupuk;
    private int jumlah;
}
