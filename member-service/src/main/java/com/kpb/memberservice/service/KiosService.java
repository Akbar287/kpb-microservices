package com.kpb.memberservice.service;

import com.kpb.memberservice.domain.Kios;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.kios.KiosCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.dto.kios.KiosResponseDTO;
import com.kpb.memberservice.dto.kios.KiosWorkingAreaCreateUpdateDTO;

import java.util.List;

public interface KiosService {
    public PaginationResponse findAll(int page, int size, String sort, String search, Long userId, String role);
    public List<KiosListResponseDTO> findAllWithoutPagination(Long distributor);
    public KiosResponseDTO findDetail(Long kiosId);
    public KiosResponseDTO findDetailByUser(Long kiosId);
    public KiosResponseDTO findDetailByPoktanUser(Long poktanUserId);
    public KiosResponseDTO findKiosDetailByUser(Long user);
    public List<KiosListResponseDTO> findKiosListByPenyuluh(Long penyuluhUserId);
    public void create(KiosCreateUpdateRequestDTO dto);
    public void update(Long kiosId, KiosCreateUpdateRequestDTO dto);
    public void updateWorkingArea(Long kiosId, List<KiosWorkingAreaCreateUpdateDTO> dto);
    public void updateDistributor(Long kiosId, Long distributorId);
    public void delete(Long kiosId);
}
