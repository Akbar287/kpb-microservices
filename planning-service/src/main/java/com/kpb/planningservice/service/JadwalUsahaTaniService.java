package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniRequestDTO;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniResponseDTO;

import java.util.List;

public interface JadwalUsahaTaniService {
    public List<JadwalUsahaTaniResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create(JadwalUsahaTaniRequestDTO dto);
    public void update(Long jadwalUsahaTaniId, JadwalUsahaTaniRequestDTO dto);
    public void delete(Long jadwalUsahaTaniId);
}
