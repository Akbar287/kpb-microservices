package com.kpb.memberservice.service;

import com.kpb.clientservice.web.Member.PoktanResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.FarmerListResponseDTO;
import com.kpb.memberservice.dto.farmerGroup.*;

import java.util.List;

public interface FarmerGroupService {
    public PaginationResponse findAllFarmerGroups(int page, int size, String sort, String search, Long kios);
    public PaginationResponse findAllFarmGroupsByPenyuluh(int page, int size, String sort, String search, String role, Long userId);
    public List<FarmerListResponseDTO> findAllFarmerLinked(Long poktanId);
    public List<FarmerGroupListResponseDTO> findAllPoktanByArea(String provinsi,String kabupaten,String kecamatan,String desa);
    public List<Long> findListPetaniByPoktanUserId(Long poktanUserId);
    public List<FarmerGroupListResponseDTO> findAllFarmerGroupsWithoutPagination();
    public void addFarmerToGroup(Long farmerGroupId, FarmerGroupAddFarmerRequestDTO dto);
    public void addFarmerToFarmerGroupByUserPoktan(Long userFarmerGroupId, FarmerGroupAddFarmerRequestDTO dto);
    public FarmerGroupDetailResponseDTO findFarmerGroup(Long farmerGroupId);
    public PoktanResponse findFarmerGroupByPoktanId(Long poktanId);
    public PoktanResponse findFarmerGroupByUserId(Long poktanUserId);
    public FarmerGroupDetailResponseDTO findFarmerGroupByPetani(Long user);
    public void createFarmerGroup(FarmerGroupCreateRequestDTO dto);
    public FarmerGroupDetailResponseDTO updateFarmerGroup(Long farmerGroupId, FarmerGroupUpdateRequestDTO dto);
    public void deleteFarmerGroup(Long farmerGroupId);
}
