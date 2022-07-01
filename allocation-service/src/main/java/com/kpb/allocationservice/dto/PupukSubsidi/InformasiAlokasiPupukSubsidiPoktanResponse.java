package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.Member.KiosResponse;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InformasiAlokasiPupukSubsidiPoktanResponse {
    private Long poktanId;
    private String namaPoktan;
    private KiosResponse kios;
    private List<InformasiSubPetaniAlokasiResponse> petani;
}
