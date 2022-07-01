package com.kpb.memberservice.dto.distributor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.*;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanListResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorDetailResponseDTO {
    private Long distributorId;
    private String nama;
    private String email;
    private String username;
    private String nomorTelepon;
    private String fileGambar;
    private String alamat;
    private String nomorAhu;
    private String jenisDistributor;
    private int status;
    private String createdAt;
    private String updatedAt;

    private PabrikanListResponseDTO pabrikan;
    private List<KiosListResponseDTO> kios;
    private List<AreaResponse> area;
    private List<AkunBankResponse> akunBank;
    private List<WilayahKerjaDistributor> wilayahKerjaDistributors;
}
