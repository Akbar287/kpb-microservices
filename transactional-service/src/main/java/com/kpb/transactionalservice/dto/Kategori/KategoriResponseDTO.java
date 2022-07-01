package com.kpb.transactionalservice.dto.Kategori;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.InputStream;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KategoriResponseDTO {
    private Long kategoriId;
    private String namaKategori;
    private String fileGambar;
    private String deskripsi;
    private String createdAt;
    private String updatedAt;
    private int produk;
}
