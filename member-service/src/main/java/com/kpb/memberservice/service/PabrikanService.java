package com.kpb.memberservice.service;

import com.kpb.clientservice.web.Member.PabrikanResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.pabrikan.PabrikanCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanDetailResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanListResponseDTO;

import java.util.List;

public interface PabrikanService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<PabrikanListResponseDTO> findAllWithoutPagination();
    public List<Long> findListKiosByPabrikanUserId(Long pabrikanUserId);
    public PabrikanDetailResponseDTO findDetail(Long pabrikanId);
    public PabrikanResponse findDetailByUserId(Long pabrikanUserId);
    public void create(PabrikanCreateUpdateRequestDTO dto);
    public void update(Long pabrikanId, PabrikanCreateUpdateRequestDTO dto);
    public void delete(Long pabrikanId);
}
