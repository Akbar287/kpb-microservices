package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;

import java.util.List;

public interface DinasPoptService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<DinasPoptListResponseDTO> findAllList();
    public DinasPoptDetailResponseDTO findDetail(Long dinasPoptId);
    public void create(DinasPoptCreateUpdateRequestDTO dto);
    public void update(Long dinasPoptId, DinasPoptCreateUpdateRequestDTO dto);
    public void delete(Long dinasPoptId);
}
