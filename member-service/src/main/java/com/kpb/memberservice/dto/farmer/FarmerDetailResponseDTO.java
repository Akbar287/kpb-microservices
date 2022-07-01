package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.*;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerDetailResponseDTO {
    private Long petaniId;
    private Long userId;
    private String nik;
    private String nama;
    private String email;
    private String alamat;
    private String username;
    private String fileGambar;
    private String nomorTelepon;
    private String ibuKandung;
    private String statusPetani;
    private String statusPernikahan;
    private String tempatLahir;
    private String tanggalLahir;
    private String agama;
    private String pendidikan;
    private String nomorKK;
    private String jenisKelamin;
    private String jenisPetani;

    private Kepemilikan kepemilikan;
    private List<LuasLahan> luasLahan;
    private List<AreaResponse> area;
    private KeluargaPetani keluargaPetani;
    private List<AsetPetani> asetPetani;
    private List<AkunBankResponse> akunBank;
}
