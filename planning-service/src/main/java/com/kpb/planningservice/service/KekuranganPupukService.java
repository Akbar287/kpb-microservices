package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukResponseDTO;

import java.util.List;

public interface KekuranganPupukService {
    public List<KekuranganPupukResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create( KekuranganPupukCreateUpdateRequestDTO dto);
    public void update(Long kekuranganPupukId, KekuranganPupukCreateUpdateRequestDTO dto);
    public void delete(Long kekuranganPupukId);
}
