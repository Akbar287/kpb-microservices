package com.kpb.transactionalservice.dto.TransaksiDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiDetailCreateUpdateRequestDTO {
    private Long transaksiId;
    private Long produkId;
    private Long petaniId;
    private int tahun;
    private int masaTanam;
    private String nama;
    private String komoditas;
    private String jenis;
    private int jumlah;
    private int harga;
    private boolean subsidi;
    private int total;
}
