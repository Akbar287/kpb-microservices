package com.kpb.planningservice.service;

import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniRequestDTO;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniResponseDTO;
import java.util.List;

public interface BiayaUsahaTaniService {
    public List<BiayaTaniResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create(BiayaTaniRequestDTO dto);
    public void update(Long biayaTaniId, BiayaTaniRequestDTO dto);
    public void delete(Long biayaTaniId);
}
