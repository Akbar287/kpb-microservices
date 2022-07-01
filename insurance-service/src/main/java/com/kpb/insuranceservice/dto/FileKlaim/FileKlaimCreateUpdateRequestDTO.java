package com.kpb.insuranceservice.dto.FileKlaim;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FileKlaimCreateUpdateRequestDTO {
    private Long klaimId;
    private Long dinasPoptId;
    private String namaFile;
    private String namaDokumen;
    private String jenisKlaimFile;
}
