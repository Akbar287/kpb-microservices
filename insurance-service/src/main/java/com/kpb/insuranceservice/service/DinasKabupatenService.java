package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;

import java.util.List;

public interface DinasKabupatenService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<DinasKabupatenListResponseDTO> findAllList();
    public DinasKabupatenDetailResponseDTO findDetail(Long dinasKabupatenId);
    public void create(DinasKabupatenCreateUpdateRequestDTO dto);
    public void update(Long dinasKabupatenId, DinasKabupatenCreateUpdateRequestDTO dto);
    public void delete(Long dinasKabupatenId);
}
