package com.kpb.allocationservice.dto.AlokasiPupukSubsidi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlokasiResponseDTO {
    private Long produkId;
    private String namaPupuk;
    private String kategori;
    private String jenis;
    private int harga;
    private int harga_kios_subsidi;
    private int harga_subsidi;
    private int biaya_kios;
    private int biaya_kios_subsidi;
    private int jumlah;
    private int digunakan;
    private int sisa;
    private int stokAwal;
    private int stokAkhir;
    private int stokSubsidi;
    private int stokPenyaluran;
}
