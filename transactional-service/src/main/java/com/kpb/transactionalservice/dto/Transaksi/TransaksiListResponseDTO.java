package com.kpb.transactionalservice.dto.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiListResponseDTO {
    private Long transaksiId;
    private String namaTransaksi;
    private String status;
    private int grandTotal;
    private boolean isTransferKios;
    private boolean isPenebusan;
    private String createdAt;
    private String updatedAt;
}
