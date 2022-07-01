package com.kpb.allocationservice.dto.AlokasiPupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NamaPupukDanJumlahHelper {
    private String namaPupuk;
    private int jumlah;
}
