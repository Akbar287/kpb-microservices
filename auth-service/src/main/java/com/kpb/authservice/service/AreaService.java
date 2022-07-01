package com.kpb.authservice.service;

import com.kpb.authservice.dto.area.AreaDetailResponseDTO;
import com.kpb.authservice.dto.area.AreaListCreateUpdateDTO;

import java.util.List;

public interface AreaService {
    public List<AreaDetailResponseDTO> findDetail(Long userId);
    public List<Long> getUserIdInAreaByForm(String provinsi,String kabupaten,String kecamatan,String desa);
    public AreaDetailResponseDTO createArea(Long userId, AreaListCreateUpdateDTO dto);
    public void updateArea(Long areaId, AreaListCreateUpdateDTO dto);
    public void deleteArea(Long areaId);
}
