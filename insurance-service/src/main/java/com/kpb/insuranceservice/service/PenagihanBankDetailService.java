package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailListResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailResponseDTO;

import java.util.List;

public interface PenagihanBankDetailService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public List<PenagihanBankDetailListResponseDTO> findAllList();
    public PenagihanBankDetailResponseDTO findDetail(Long penagihanBankDetailId);
    public void create(PenagihanBankDetailCreateUpdateRequestDTO dto);
    public void update(Long penagihanBankDetailId, PenagihanBankDetailCreateUpdateRequestDTO dto);
    public void delete(Long penagihanBankDetailId);
}
