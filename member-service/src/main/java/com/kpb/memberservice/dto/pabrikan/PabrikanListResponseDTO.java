package com.kpb.memberservice.dto.pabrikan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PabrikanListResponseDTO {
    private Long pabrikanId;
    private String nama;
    private String jenisPabrik;
    private String alamat;
}
