package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.NamaPupukDanJumlahHelper;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SisaPupukSubsidiResponseDTO {
    private Long pupukSubsidiId;
    private String nama;
    private String username;
    private int tahun;
    private int masaTanam;

    private List<NamaPupukDanJumlahHelper> pupuk;
}
