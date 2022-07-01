package com.kpb.transactionalservice.dto.TransaksiDetail;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiDetailListResponseDTO {
    private Long transaksiDetailId;
    private int jumlah;
    private int harga;
    private boolean subsidi;
    private int total;

    private ProdukResponseDTO produk;
}
