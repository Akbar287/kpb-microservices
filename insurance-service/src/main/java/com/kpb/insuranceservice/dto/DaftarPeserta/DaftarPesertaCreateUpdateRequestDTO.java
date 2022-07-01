package com.kpb.insuranceservice.dto.DaftarPeserta;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DaftarPesertaCreateUpdateRequestDTO {
    private Long dinasKabupatenId;
    private int tahun;
    private int masaTanam;
    private String namaFile;
    private String namaDokumen;
    private int keterangan;
}
