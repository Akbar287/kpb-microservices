package com.kpb.clientservice.web.Alokasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SisaPupukRequest {
    private Long petaniId;
    private int tahun;
    private int masaTanam;
    private String pupuk;
}
