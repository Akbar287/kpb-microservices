package com.kpb.planningservice.dto.BiayaUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BiayaTaniResponseDTO {
    private Long biayaTaniId;
    private Long rencanaUsahaTaniId;
    private String jenisBiayaUsahaTani;
    private String jenis;
    private Integer harga;
    private Integer kuantitas;
    private String satuan;
}
