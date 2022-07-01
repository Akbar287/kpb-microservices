package com.kpb.transactionalservice.dto.Produk;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.dto.Jenis.JenisResponseDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriResponseDTO;
import com.kpb.transactionalservice.dto.Stok.StokResponseDTO;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdukResponseDTO {
    private Long produkId;
    private String namaProduk;
    private String satuan;
    private String fileGambar;
    private String deskripsi;
    private boolean isPenebusan;

    private KategoriResponseDTO kategori;
    private KiosResponse kios;
    private JenisResponseDTO jenis;
    private HargaResponseDTO harga;
    private StokResponseDTO stok;
}
