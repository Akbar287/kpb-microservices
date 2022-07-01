package com.kpb.memberservice.service;

import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.penyuluh.PenyuluhCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.penyuluh.PenyuluhListResponseDTO;

import java.util.List;

public interface PenyuluhService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public PenyuluhListResponseDTO findDetail(Long penyuluhId);
    public List<Long> getListKiosId(Long penyuluhUserId);
    public void create(PenyuluhCreateUpdateRequestDTO dto);
    public void update(Long penyuluhId, PenyuluhCreateUpdateRequestDTO dto);
    public void updateKios(Long penyuluhId, Long kiosId);
    public void delete(Long penyuluhId);
}
