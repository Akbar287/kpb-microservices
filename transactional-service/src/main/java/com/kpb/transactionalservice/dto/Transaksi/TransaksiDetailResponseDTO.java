package com.kpb.transactionalservice.dto.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.transactionalservice.domain.PenebusanPupukStatus;
import com.kpb.transactionalservice.dto.PenebusanPupuk.PenebusanPupukResponseDTO;
import com.kpb.transactionalservice.dto.TransaksiDetail.TransaksiDetailListResponseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiDetailResponseDTO {
    private Long transaksiId;
    private String namaTransaksi;
    private int grandTotal;
    private String metodePembayaran;
    private String virtualAccount;
    private boolean statusKredit;
    private boolean verifikasiKredit;
    private int statusRegistrasiKredit;
    private boolean isTransferKios;
    private boolean isPenebusan;

    private PenebusanPupukResponseDTO penebusanPupuk;
    private List<PenebusanPupukStatus> penebusanPupukStatus;
    private List<TransaksiDetailListResponseDTO> transaksiDetail;
    private KiosResponse kios;
    private CreateUserResponse user;


}
