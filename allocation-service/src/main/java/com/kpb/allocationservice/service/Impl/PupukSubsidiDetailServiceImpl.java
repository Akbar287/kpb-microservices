package com.kpb.allocationservice.service.Impl;

import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.allocationservice.dto.PupukSubsidi.PupukSubsidiDetailResponseDTO;
import com.kpb.allocationservice.dto.PupukSubsidiDetail.PupukSubsidiDetailCreateUpdateRequestDTO;
import com.kpb.allocationservice.repository.PupukSubsidiDetailRepository;
import com.kpb.allocationservice.repository.PupukSubsidiStatusRepository;
import com.kpb.allocationservice.service.PupukSubsidiDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PupukSubsidiDetailServiceImpl implements PupukSubsidiDetailService {

    @Autowired
    private PupukSubsidiDetailRepository pupukSubsidiDetailRepository;

    @Autowired
    private PupukSubsidiStatusRepository pupukSubsidiStatusRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort) {
        return null;
    }

    @Override
    public PupukSubsidiDetailResponseDTO findDetail(Long pupukSubsidiDetailId) {
        return null;
    }

    @Override
    public void create(PupukSubsidiDetailCreateUpdateRequestDTO dto) {

    }

    @Override
    public void update(Long pupukSubsidiDetailId, PupukSubsidiDetailCreateUpdateRequestDTO dto) {

    }

    @Override
    public void delete(Long pupukSubsidiDetailId) {

    }
}
