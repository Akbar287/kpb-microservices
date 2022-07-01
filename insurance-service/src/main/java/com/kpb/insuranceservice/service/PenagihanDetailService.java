package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailDetailResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailListResponseDTO;

import java.util.List;

public interface PenagihanDetailService {
    public PaginationResponse findAll(int page, int size, String sort);
    public List<PenagihanDetailListResponseDTO> findAllList();
    public PenagihanDetailDetailResponseDTO findDetail(Long penagihanDetailId);
    public void create(PenagihanDetailCreateUpdateRequestDTO dto);
    public void update(Long penagihanDetailId, PenagihanDetailCreateUpdateRequestDTO dto);
    public void delete(Long penagihanDetailId);
}
