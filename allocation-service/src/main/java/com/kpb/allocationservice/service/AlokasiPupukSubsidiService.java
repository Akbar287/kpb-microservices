package com.kpb.allocationservice.service;

import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.clientservice.web.Alokasi.AlokasiPupukSubsidiResponse;

public interface AlokasiPupukSubsidiService {
    public AlokasiPupukSubsidiResponse getSisaAlokasiPubersNyTransaksi(Long transaksiDetailId);
    public void create(AlokasiPupukSubsidiCreateUpdateRequestDTO dto);
    public void update(Long alokasiPupukSubsidiId, AlokasiPupukSubsidiCreateUpdateRequestDTO dto);
    public void delete(AlokasiPupukSubsidiCreateUpdateRequestDTO dto);
}
