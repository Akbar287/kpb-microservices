package com.kpb.allocationservice.dto.AlokasiPupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlokasiPupukSubsidiCreateUpdateRequestDTO {
    private Long petaniId;
    private int tahun;
    private int masaTanam;
    private Long transaksiId;
    private String namaPupuk;
    private int jumlah;
}
