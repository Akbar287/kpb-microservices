package com.kpb.clientservice.web.Alokasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SisaPupukResponse {
    private Long pupukSubsidiId;
    private String nama;
    private String username;
    private int tahun;
    private int masaTanam;

    private List<NamaPupukDanJumlahHelper> pupuk;
}
