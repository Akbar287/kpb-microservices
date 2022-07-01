package com.kpb.clientservice.web.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StokProdukResponse {
    private Long produkId;
    private Long kiosId;
    private Long distributorId;
    private int tahun;
    private String bulan;
    private String kategori;
    private String jenis;
    private int harga;
    private int harga_kios_subsidi;
    private int harga_subsidi;
    private int biaya_kios;
    private int biaya_kios_subsidi;
    private int stokAwal;
    private int stokAkhir;
    private int stokSubsidi;
    private int stokPenyaluran;
}
