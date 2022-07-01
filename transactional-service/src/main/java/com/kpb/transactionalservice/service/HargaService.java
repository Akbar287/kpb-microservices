package com.kpb.transactionalservice.service;

import com.kpb.transactionalservice.dto.Harga.HargaRequestDTO;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;

public interface HargaService {
    public HargaResponseDTO findDetail(Long produkId);
    public void create(HargaRequestDTO dto);
    public void update(Long hargaId, HargaRequestDTO dto);
    public void delete(Long hargaId);
}
