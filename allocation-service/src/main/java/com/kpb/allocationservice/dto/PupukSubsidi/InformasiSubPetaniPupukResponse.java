package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InformasiSubPetaniPupukResponse {
    private String namaPupuk;
    private int jumlahAwal;
    private int digunakan;
    private int sisa;
}
