package com.kpb.transactionalservice.dto.Stok;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StokRequestDTO {
    private Long produkId;
    private Long kiosId;
    private Long distributorId;
    private String bulan;
    private int tahun;
    private int stokAwal;
    private int stokSubsidi;
    private int stokPenyaluran;
    private int stokAkhir;
}
