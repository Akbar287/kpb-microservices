package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiListResponseDTO;

import java.util.List;

public interface PelaksanaAsuransiService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<PelaksanaAsuransiListResponseDTO> findAllList();
    public PelaksanaAsuransiDetailResponseDTO findDetail(Long pelaksanaAsuransiId);
    public void create(PelaksanaAsuransiCreateUpdateRequestDTO dto);
    public void update(Long pelaksanaAsuransiId, PelaksanaAsuransiCreateUpdateRequestDTO dto);
    public void delete(Long pelaksanaAsuransiId);
}
