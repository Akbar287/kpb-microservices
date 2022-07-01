package com.kpb.memberservice.dto.farmerGroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.Kios;
import com.kpb.memberservice.domain.Petani;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerGroupDetailResponseDTO {
    private Long poktanId;
    private String namaPoktan;
    private String email;
    private String usernamePoktan;
    private String nomorTelepon;
    private String fileGambar;
    private String jenisPoktan;
    private String namaKetuaPoktan;
    private boolean registrasi;
    private boolean statusKonfirmasi;
    private String alamat;
    private String provinsi;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private String createdAt;
    private String updatedAt;
    private KiosListResponseDTO kios;

    private List<AkunBankResponse> akunBankResponseList;

}
