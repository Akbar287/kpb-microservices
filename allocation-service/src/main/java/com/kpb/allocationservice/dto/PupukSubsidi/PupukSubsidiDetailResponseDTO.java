package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.allocationservice.domain.PupukSubsidiStatus;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiPupukSubsidiResponseDTO;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiResponseDTO;
import com.kpb.allocationservice.dto.PupukSubsidiDetail.PupukSubsidiResponseDTO;
import com.kpb.clientservice.web.Member.KiosResponse;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PupukSubsidiDetailResponseDTO {
    private Long pupukSubsidiId;
    private Long petaniId;
    private String nama;
    private int tahun;
    private int masaTanam;
    private String komoditas;
    private String subsektor;
    private boolean isDigunakan;
    private int luasLahan;
    private String pesan;

    private KiosResponse kiosResponse;
    private List<PupukSubsidiStatus> pupukSubsidiStatus;
    private List<AlokasiResponseDTO> alokasi;
}
