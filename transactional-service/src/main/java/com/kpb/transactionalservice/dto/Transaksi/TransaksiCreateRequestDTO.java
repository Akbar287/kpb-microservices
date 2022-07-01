package com.kpb.transactionalservice.dto.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.transactionalservice.dto.PenebusanPupuk.PenebusanPupukRequestDTO;
import com.kpb.transactionalservice.dto.TransaksiDetail.TransaksiDetailCreateUpdateRequestDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiCreateRequestDTO {
    private Long userId;
    private Long kiosId;
    private String namaTransaksi;
    private int grandTotal;
    private String metodePembayaran;
    private String virtualAccount;
    private boolean statusKredit;
    private boolean verifikasiKredit;
    private int statusRegistrasiKredit;
    private boolean isTransferKios;
    private boolean isPenebusan;

    private PenebusanPupukRequestDTO penebusanPupuk;
    private List<TransaksiDetailCreateUpdateRequestDTO> transaksi;
}
