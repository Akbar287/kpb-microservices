package com.kpb.transactionalservice.service;

import com.kpb.transactionalservice.dto.Jenis.JenisRequestDTO;
import com.kpb.transactionalservice.dto.Jenis.JenisResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;

public interface JenisService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public JenisResponseDTO findDetail(Long jenisId);
    public void create(JenisRequestDTO dto);
    public void update(Long jenisId, JenisRequestDTO dto);
    public void delete(Long jenisId);
}
