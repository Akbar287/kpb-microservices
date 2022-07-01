package com.kpb.insuranceservice.dto.Klaim;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KlaimCreateUpdateRequestDTO {
    private Long pendaftaranAsuransiId;
    private Long usahaTaniId;
    private String namaKlaim;
    private String kelompokKlaim;
    private int kode;
    private boolean isTanggunganAsuransi;
    private boolean isTanggunganPetani;
    private String keterangan;
}
