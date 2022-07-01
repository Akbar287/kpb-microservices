package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.Klaim.KlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;

import java.util.List;

public interface KlaimService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<KlaimListResponseDTO> findAllList();
    public KlaimDetailResponseDTO findDetail(Long klaimId);
    public void create(KlaimCreateUpdateRequestDTO dto);
    public void update(Long klaimId, KlaimCreateUpdateRequestDTO dto);
    public void delete(Long klaimId);
}
