package com.kpb.insuranceservice.dto.UsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UsahaTaniDetailResponseDTO {
    private Long usahaTaniId;
    private int tahun;
    private int masaTanam;
    private int luasArea;
    private String namaKomoditas;
    private String subsektor;
    private boolean isAktif;

    private PendaftaranAsuransi pendaftaranAsuransi;
    private CreateUserResponse user;
}
