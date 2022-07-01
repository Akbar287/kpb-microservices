package com.kpb.planningservice.dto.JadwalUsahaTani;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JadwalUsahaTaniRequestDTO {
    private Long rencanaUsahaTaniId;
    private Integer masaTanam;
    private Integer airTanahDrainase;
    private Integer pengolahanTanah;
    private Integer persemaian;
    private Integer penanaman;
    private Integer pemeliharaan;
    private Integer semprotanHerbisida;
    private Integer penyemprotanFungisidaPertama;
    private Integer penyemprotanFungisidaKedua;
    private Integer penyemprotanFungisidaKetiga;
    private Integer pemupukanDasar;
    private Integer pemupukanPertama;
    private Integer pemupukanKedua;
    private Integer penyemprotanInsektisidaGandaBuah;
    private Integer panen;
    private String gabahKeringPanen;
    private String gabahKeringGiling;
    private Integer beras;
    private String hasilPanen;
    private String deskripsi;
    private Integer pendapatanKotor;
}
