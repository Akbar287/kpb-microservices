package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniDetailResponseDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniListResponseDTO;

import java.util.List;

public interface UsahaTaniService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<UsahaTaniListResponseDTO> findAllList();
    public UsahaTaniDetailResponseDTO findDetail(Long usahaTaniId);
    public void create(UsahaTaniCreateUpdateRequestDTO dto);
    public void update(Long usahaTaniId, UsahaTaniCreateUpdateRequestDTO dto);
    public void delete(Long usahaTaniId);
}
