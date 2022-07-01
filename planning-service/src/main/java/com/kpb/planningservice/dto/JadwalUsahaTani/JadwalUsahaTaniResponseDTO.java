package com.kpb.planningservice.dto.JadwalUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JadwalUsahaTaniResponseDTO {
    private Long jadwalUsahaTaniId;
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    private String airTanahDrainase;
    private String pengolahanTanah;
    private String persemaian;
    private String penanaman;
    private String pemeliharaan;
    private String semprotanHerbisida;
    private String penyemprotanFungisidaPertama;
    private String penyemprotanFungisidaKedua;
    private String penyemprotanFungisidaKetiga;
    private String pemupukanDasar;
    private String pemupukanPertama;
    private String pemupukanKedua;
    private String penyemprotanInsektisidaGandaBuah;
    private String panen;
    private String gabahKeringPanen;
    private String gabahKeringGiling;
    private Integer beras;
    private String hasilPanen;
    private String deskripsi;
    private Integer pendapatanKotor;
}
