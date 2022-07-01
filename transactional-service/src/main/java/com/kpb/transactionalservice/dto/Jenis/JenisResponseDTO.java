package com.kpb.transactionalservice.dto.Jenis;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JenisResponseDTO {
    private Long jenisId;
    private String nama;
    private String fileGambar;
    private String deskripsi;
    private String createdAt;
    private String updatedAt;
    private int produk;
}
