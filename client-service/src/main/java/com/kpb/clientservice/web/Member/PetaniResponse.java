package com.kpb.clientservice.web.Member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PetaniResponse {
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
}
