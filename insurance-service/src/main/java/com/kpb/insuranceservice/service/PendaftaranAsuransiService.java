package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiListResponseDTO;

import java.util.List;

public interface PendaftaranAsuransiService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<PendaftaranAsuransiListResponseDTO> findAllList();
    public PendaftaranAsuransiDetailResponseDTO findDetail(Long pendaftaranAsuransiId);
    public void create(PendaftaranAsuransiCreateUpdateRequestDTO dto);
    public void update(Long pendaftaranAsuransiId, PendaftaranAsuransiCreateUpdateRequestDTO dto);
    public void delete(Long pendaftaranAsuransiId);
}
