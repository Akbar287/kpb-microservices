package com.kpb.memberservice.dto.penyuluh;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.memberservice.domain.Kios;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PenyuluhListResponseDTO {
    private Long penyuluhId;
    private String nama;
    private String fileGambar;
    private String email;
    private String username;
    private String nomorTelepon;
    private String alamat;
    private boolean verifikasi;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<AreaResponse> area;
    private List<AkunBankResponse> akun_bank;
    private List<Kios> kios;
}
