package com.kpb.memberservice.dto.kios;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.Distributor;
import com.kpb.memberservice.domain.Penyuluh;
import com.kpb.memberservice.domain.Poktan;
import com.kpb.memberservice.domain.WilayahKerjaKios;
import com.kpb.memberservice.dto.distributor.DistributorListResponseDTO;
import com.kpb.memberservice.dto.farmerGroup.FarmerGroupListResponseDTO;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KiosResponseDTO {
    private Long kiosId;
    private String nama;
    private String fileGambar;
    private String email;
    private String username;
    private String nomorTelepon;
    private String kodePihc;
    private String namaKomoditas;
    private Boolean verifikasiPasarBebas;
    private String alamat;
    private String createdAt;
    private String updatedAt;
    private List<WilayahKerjaKios> wilayahKerjaKios;
    private List<AreaResponse> areaResponse;
    private List<AkunBankResponse> akunBank;
    private List<FarmerGroupListResponseDTO> poktan;
    private DistributorListResponseDTO distributor;
    private Penyuluh penyuluh;
}
