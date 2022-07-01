package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InformasiSubPetaniAlokasiResponse {
    private Long petaniId;
    private Long pupukSubsidiId;
    private String nik;
    private String nama;
    private int tahun;
    private int masaTanam;
    private int luasLahan;
    private String komoditas;
    private String subsektor;
    private List<InformasiSubPetaniPupukResponse> pupuk;
}
