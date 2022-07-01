package com.kpb.planningservice.service;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiResponseDTO;

import java.util.List;

public interface RealisasiPupukSubsidiService {
    public List<RealisasiPupukSubsidiResponseDTO> findDetail(Long rencanaUsahaTaniId);
    public void create( RealisasiPupukSubsidiCreateUpdateRequestDTO dto);
    public void update(Long realisaisPupukSubsidiId, RealisasiPupukSubsidiCreateUpdateRequestDTO dto);
    public void delete(Long realisaisPupukSubsidiId);
}
