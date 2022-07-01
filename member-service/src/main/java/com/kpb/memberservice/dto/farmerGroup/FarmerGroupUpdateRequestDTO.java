package com.kpb.memberservice.dto.farmerGroup;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerGroupUpdateRequestDTO {
    private String namaPoktan;
    private String usernamePoktan;
    private String passwordPoktan;
    private String namaKetuaPoktan;
    private String nomorTelepon;
    private String jenisPoktan;
    private String email;
    private String alamat;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private String provinsi;
    private int kodeProvinsi;
    private int kodeKabupaten;
    private int kodeKecamatan;
    private int kodeDesa;
}
