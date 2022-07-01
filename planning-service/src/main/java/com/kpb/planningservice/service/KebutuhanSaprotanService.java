package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanRequestDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanResponseDTO;

import java.util.List;

public interface KebutuhanSaprotanService {
    public List<KebutuhanSaprotanResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create( KebutuhanSaprotanRequestDTO dto);
    public void update(Long kebutuhanSaprotanId, KebutuhanSaprotanRequestDTO dto);
    public void delete(Long kebutuhanSaprotanId);
}
