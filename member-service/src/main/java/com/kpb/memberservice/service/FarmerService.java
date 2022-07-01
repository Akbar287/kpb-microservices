package com.kpb.memberservice.service;

import com.kpb.clientservice.web.Member.KomoditasSubsektorResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.*;

import java.util.List;

public interface FarmerService {
    public PaginationResponse findAllFarmer(int page, int size, String sort, String search, Long kios);
    public List<FarmerListResponseDTO> findAllFarmerWithoutPagination();
    public FarmerDetailResponseDTO findFarmerDetail(Long farmerId);
    public Boolean checkNik(String nik);
    public KomoditasSubsektorResponse findKomoditasAndSubsektor(Long petaniId);
    public List<Long> searchNik(String nik);
    public FarmerDetailResponseDTO farmerDetailResponseDTOByUser(Long userId);
    public PaginationResponse farmerDetailResponseDTOByPoktan(int page, int size, String sort, String search, Long userId);
    public PaginationResponse farmerDetailResponseDTOByAllRole(int page, int size, String sort, String search, String role, Long userId);
    public FarmerDetailResponseDTO updateFarmer(Long farmerId, FarmerUpdateRequestDTO farmerUpdateRequestDTO);
    public void updateAssetFarmer(Long farmerId, List<FarmerAssetCreateUpdateRequestDTO> farmerAssetCreateUpdateRequestDTO);
    public void updateFamilyFarmer(Long farmerId, FarmerFamilyCreateUpdateRequestDTO farmerFamilyCreateUpdateRequestDTO);
    public void updateOwnershipFarmer(Long farmerId, FarmerOwnershipCreateUpdateRequestDTO farmerOwnershipCreateUpdateRequestDTO);
    public void updateSurfaceAreaFarmer(Long petaniId, FarmerSurfaceAreaCreateUpdateRequestDTO farmerSurfaceAreaCreateUpdateRequestDTO);
    public void deleteFarmer(Long farmerId);
}
