package com.kpb.allocationservice.dto.PupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.allocationservice.dto.PupukSubsidiDetail.PupukSubsidiDetailCreateUpdateRequestDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PupukSubsidiCreateUpdateRequestDTO {
    private Long petaniId;
    private Long kiosId;
    private int tahun;
    private int masaTanam;
    private String komoditas;
    private String subsektor;
    private boolean isDigunakan;
    private int luasLahan;
    private String pesan;

    private List<PupukSubsidiDetailCreateUpdateRequestDTO> pupukSubsidiDetail;
}
