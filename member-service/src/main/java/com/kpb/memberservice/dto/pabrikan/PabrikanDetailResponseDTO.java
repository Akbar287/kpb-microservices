package com.kpb.memberservice.dto.pabrikan;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.Distributor;
import com.kpb.memberservice.dto.distributor.DistributorListResponseDTO;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PabrikanDetailResponseDTO {
    private Long pabrikanId;
    private String nama;
    private String fileGambar;
    private String email;
    private String username;
    private String nomorTelepon;
    private String jenisPabrik;
    private String alamat;
    private String createdAt;
    private String updatedAt;

    private List<DistributorListResponseDTO> distributor;
    private List<AreaResponse> area;
    private List<AkunBankResponse> akunBank;
}
