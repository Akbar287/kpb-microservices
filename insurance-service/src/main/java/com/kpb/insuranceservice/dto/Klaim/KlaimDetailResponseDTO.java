package com.kpb.insuranceservice.dto.Klaim;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.FileKlaim;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.domain.UsahaTani;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KlaimDetailResponseDTO {
    private Long klaimId;
    private String namaKlaim;
    private String kelompokKlaim;
    private int kode;
    private boolean isTanggunganAsuransi;
    private boolean isTanggunganPetani;
    private String keterangan;

    private PendaftaranAsuransi pendaftaranAsuransi;
    private UsahaTani usahaTani;
    private List<FileKlaim> fileKlaim;
}
