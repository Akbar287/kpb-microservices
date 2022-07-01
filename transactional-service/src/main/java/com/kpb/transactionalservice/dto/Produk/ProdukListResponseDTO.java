package com.kpb.transactionalservice.dto.Produk;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdukListResponseDTO {
    private Long produkId;
    private String namaProduk;
    private String satuan;
    private String fileGambar;
    private String deskripsi;
    private boolean isPenebusan;
}
