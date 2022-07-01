package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanRequestDTO;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanResponseDTO;

import java.util.List;

public interface JenisTanamanService {
    public List<JenisTanamanResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create( JenisTanamanRequestDTO dto);
    public void update(Long jenisTanamanId, JenisTanamanRequestDTO dto);
    public void delete(Long jenisTanamanId);
}
