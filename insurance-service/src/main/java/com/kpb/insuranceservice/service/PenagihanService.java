package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanDetailResponseDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanListResponseDTO;

import java.util.List;

public interface PenagihanService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<PenagihanListResponseDTO> findAllList();
    public PenagihanDetailResponseDTO findDetail(Long penagihanId);
    public void create(PenagihanCreateUpdateRequestDTO dto);
    public void update(Long penagihanId, PenagihanCreateUpdateRequestDTO dto);
    public void delete(Long penagihanId);
}
