package com.kpb.transactionalservice.service;

import com.kpb.transactionalservice.dto.Stok.StokRequestDTO;
import com.kpb.transactionalservice.dto.Stok.StokResponseDTO;

import java.util.List;

public interface StokService {
    public StokResponseDTO findDetail(Long produkId, Long distributor, Long kios, int tahun, String bulan);
    public void create(StokRequestDTO dto);
    public void update(StokRequestDTO dto);
    public void delete(Long stokId);
}
