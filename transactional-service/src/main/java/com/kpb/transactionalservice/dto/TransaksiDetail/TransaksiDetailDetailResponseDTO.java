package com.kpb.transactionalservice.dto.TransaksiDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiDetailDetailResponseDTO {
    private Long transaksiDetailId;
    private int tahun;
    private int masaTanam;
    private String komoditas;
    private String jenis;
    private int jumlah;
    private String harga;
    private boolean subsidi;
    private String total;
}
