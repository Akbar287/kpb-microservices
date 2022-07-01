package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TanggunganPetaniRequest {
    private String namaAnak;
    private int nomorAnak;
    private String tempatLahir;
    private Long tanggalLahir;
    private String pendidikan;
}
