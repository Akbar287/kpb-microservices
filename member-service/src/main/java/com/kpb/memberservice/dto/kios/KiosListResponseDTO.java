package com.kpb.memberservice.dto.kios;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KiosListResponseDTO {
    private Long kiosId;
    private Long userId;
    private String nama;
    private String username;
    private String kodePihc;
    private String namaKomoditas;
    private Boolean verifikasiPasarBebas;
}
