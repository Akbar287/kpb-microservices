package com.kpb.insuranceservice.dto.PendaftaranAsuransi;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PendaftaranAsuransiCreateUpdateRequestDTO {
    private List<Long> usahaTaniId;
    private Long asuransiId;
    private String nomorAsuransi;
    private int tahun;
    private int masaTanam;
    private int waktuEfektif;
    private int waktuBerakhir;
    private String metodePembayaran;
    private int totalPembayaran;
    private String namaFile;
    private String namaDokumen;
    private boolean isAktif;
    private String informasi;
}
