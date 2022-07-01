package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.PaginationResponse;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniDetailResponseDTO;

public interface RencanaUsahaTaniService {
    public PaginationResponse findAll(int page, int size, String sort, String search, Long petani, int tahun, Long user);
    public PaginationResponse findAllPoktan(int page, int size, String sort, String search, Long poktanUserId, int tahun);
    public RencanaUsahaTaniDetailResponseDTO findDetail(Long rencanaUsahaTaniId);
    public Integer findLuasLahan(Long petaniId);
    public void create(RencanaUsahaTaniCreateUpdateRequestDTO dto);
    public void update(Long rencanaUsahaTaniId, RencanaUsahaTaniCreateUpdateRequestDTO dto);
    public void delete(Long rencanaUsahaTaniId);
}
