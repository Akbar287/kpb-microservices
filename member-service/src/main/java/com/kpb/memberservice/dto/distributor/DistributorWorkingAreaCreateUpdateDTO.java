package com.kpb.memberservice.dto.distributor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorWorkingAreaCreateUpdateDTO {
    private String namaDesa;
    private int kodeDesa;
    private String namaKecamatan;
    private int kodeKecamatan;
    private String namaKabupaten;
    private int kodeKabupaten;
    private String namaProvinsi;
    private int kodeProvinsi;
}
