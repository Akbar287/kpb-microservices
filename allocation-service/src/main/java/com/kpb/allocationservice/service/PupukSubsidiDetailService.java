package com.kpb.allocationservice.service;

import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.allocationservice.dto.PupukSubsidi.PupukSubsidiDetailResponseDTO;
import com.kpb.allocationservice.dto.PupukSubsidiDetail.PupukSubsidiDetailCreateUpdateRequestDTO;

public interface PupukSubsidiDetailService {
    public PaginationResponse findAll(int page, int size, String sort);
    public PupukSubsidiDetailResponseDTO findDetail(Long pupukSubsidiDetailId);
    public void create(PupukSubsidiDetailCreateUpdateRequestDTO dto);
    public void update(Long pupukSubsidiDetailId, PupukSubsidiDetailCreateUpdateRequestDTO dto);
    public void delete(Long pupukSubsidiDetailId);
}
