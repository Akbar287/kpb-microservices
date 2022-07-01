package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaDetailResponseDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;

import java.util.List;

public interface DaftarPesertaService {
    public PaginationResponse findAll(int page, int size, String sort, Integer tahun, Integer masa_tanam);
    public List<DaftarPesertaListResponseDTO> findAllList();
    public DaftarPesertaDetailResponseDTO findDetail(Long daftarPesertaId);
    public void create(DaftarPesertaCreateUpdateRequestDTO dto);
    public void update(Long daftarPesertaId, DaftarPesertaCreateUpdateRequestDTO dto);
    public void delete(Long daftarPesertaId);
}
