package com.kpb.memberservice.service;

import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.distributor.*;

import java.util.List;

public interface DistributorService {
    public PaginationResponse findAllDistributor (int page, int size, String sort, String search, String role, Long userId);
    public DistributorDetailResponseDTO findDetailDistributor(Long distributorId);
    public DistributorResponse findDetailDistributorByKiosFromUser(Long user);
    public DistributorResponse findDetailDistributorByPetaniFromUser(Long user);
    public DistributorResponse findDetailDistributorByPoktanFromUser(Long user);
    public List<DistributorListResponseDTO> findAllDistributorWithoutPagination();
    public List<Long> findAllKiosIdByDistributorUserId(Long distributorUserId);
    public void createDistributor(DistributorCreateRequestDTO dto);
    public void updateDistributor(Long distributorId, DistributorUpdateRequestDTO dto);
    public void updatePabrikan(Long distributorId, Long pabrikanId);
    public void updateDistributorWorkingArea(Long distributorId, List<DistributorWorkingAreaCreateUpdateDTO> dto);
    public void deleteDistributor(Long distributorId);
}
