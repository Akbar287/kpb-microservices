package com.kpb.transactionalservice.dto.Stok;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StokResponseDTO {
    private Long stokId;
    private String bulan;
    private int tahun;
    private int stokAwal;
    private int stokSubsidi;
    private int stokPenyaluran;
    private int stokAkhir;
    private KiosResponse kios;
    private DistributorResponse distributor;
}
