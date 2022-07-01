package com.kpb.transactionalservice.dto.Harga;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HargaResponseDTO {
    private Long hargaId;
    private int harga;
    private int hargaKios;
    private int hargaSubsidi;
    private int hargaKiosSubsidi;
    private int biayaKios;
    private int biayaLain;
    private int biayaKiosSubsidi;
}
