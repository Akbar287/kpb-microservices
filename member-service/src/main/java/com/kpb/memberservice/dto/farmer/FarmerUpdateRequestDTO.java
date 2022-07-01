package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerUpdateRequestDTO {
    private String statusPetani;
    private String alamat;
    private String tempatLahir;
    private String tanggalLahir;
    private String nomorKk;
    private String agama;
    private String pendidikan;
    private String statusPernikahan;
    private String ibuKandung;
    private String jenisPetani;
    private String jenisKelamin;
    private String nik;
    private String email;
    private String nama;
    private String nomorTelepon;
    private String username;
}
