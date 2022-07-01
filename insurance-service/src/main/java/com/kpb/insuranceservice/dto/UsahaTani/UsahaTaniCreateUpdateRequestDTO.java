package com.kpb.insuranceservice.dto.UsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsahaTaniCreateUpdateRequestDTO {
    private Long pendaftaranAsuransiId;
    private Long petaniId;
    private int tahun;
    private int masaTanam;
    private int luasArea;
    private String namaKomoditas;
    private String subsektor;
    private boolean isAktif;
}
