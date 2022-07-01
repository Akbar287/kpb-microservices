package com.kpb.transactionalservice.dto.Kategori;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KategoriRequestDTO {
    private String namaKategori;
    private String fileGambar;
    private String deskripsi;
}
