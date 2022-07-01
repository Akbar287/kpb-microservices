package com.kpb.insuranceservice.dto.FileKlaim;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.Klaim;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FileKlaimDetailResponseDTO {
    private Long fileKlaimId;
    private String namaFile;
    private String namaDokumen;
    private String jenisKlaimFile;
    private Klaim klaim;
}
