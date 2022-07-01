package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.Asuransi.AsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;

import java.util.List;

public interface AsuransiService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public PaginationResponse findAllList(int page, int size, String sort, String search);
    public AsuransiDetailResponseDTO findDetail(Long asuransiId);
    public void create(AsuransiCreateUpdateRequestDTO dto);
    public void update(Long asuransiId, AsuransiCreateUpdateRequestDTO dto);
    public void delete(Long asuransiId);
}
