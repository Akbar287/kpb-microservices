package com.kpb.insuranceservice.dto.Penagihan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.insuranceservice.domain.PenagihanDetail;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenagihanDetailResponseDTO {
    private Long penagihanId;
    private String batasWaktu;
    private BigInteger minimalPembayaran;
    private BigInteger saldo;
    private String status;
    private String createdAt;
    private String updatedAt;

    private List<PenagihanDetail> penagihanDetail;
    private PendaftaranAsuransi pendaftaranAsuransi;
}
