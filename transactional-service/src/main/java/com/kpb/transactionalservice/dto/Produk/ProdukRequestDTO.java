package com.kpb.transactionalservice.dto.Produk;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.transactionalservice.dto.Harga.HargaRequestDTO;
import com.kpb.transactionalservice.dto.Stok.StokRequestDTO;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdukRequestDTO {
    private String jenis;
    private String kategori;
    private String namaProduk;
    private String satuan;
    private String fileGambar;
    private String deskripsi;
    private boolean isPenebusan;

    private HargaRequestDTO harga;
}
