package com.kpb.clientservice.web.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StokProdukRequest {
    private String nama;
    private int tahun;
    private String bulan;
}
