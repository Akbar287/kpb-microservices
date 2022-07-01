package com.kpb.planningservice.dto.RencanaUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.Member.PetaniResponse;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniResponseDTO;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniResponseDTO;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanResponseDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanResponseDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukResponseDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiResponseDTO;
import lombok.Data;

import java.util.List;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RencanaUsahaTaniDetailResponseDTO {
    private Long RencanaUsahaTaniId;
    private Integer tahun;
    private Integer luasLahan;
    private String status;
    private Integer totalBiayaUsahaTani;
    private Integer grandTotal;
    private Integer prediksiPendapatan;
    private String description;

    private PetaniResponse petani;
    private List<BiayaTaniResponseDTO> biayaTani;
    private List<KebutuhanSaprotanResponseDTO> kebutuhanSaprotan;
    private List<KekuranganPupukResponseDTO> kekuranganPupuk;
    private List<JenisTanamanResponseDTO> jenisTanaman;
    private List<RealisasiPupukSubsidiResponseDTO> realisasiPupukSubsidi;
    private List<JadwalUsahaTaniResponseDTO> jadwalUsahaTani;
}
