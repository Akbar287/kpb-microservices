package com.kpb.memberservice.web;

import com.kpb.clientservice.web.Member.PoktanResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.FarmerListResponseDTO;
import com.kpb.memberservice.dto.farmerGroup.*;
import com.kpb.memberservice.service.FarmerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/members/api/farmer-group")
public class FarmGroupResource {

    @Autowired
    private FarmerGroupService farmerGroupService;

    @GetMapping()
    public ResponseEntity<PaginationResponse> findAllFarmGroups (@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "kios", required = false) Long kios) {
        PaginationResponse farmerGroupListResponseDTOS = farmerGroupService.findAllFarmerGroups(page, size, sort, search, kios);
        return ResponseEntity.ok().body(farmerGroupListResponseDTOS);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FarmerGroupListResponseDTO>> findAllFarmGroupsWithoutPagination () {
        List<FarmerGroupListResponseDTO> farmerGroupListResponseDTOS = farmerGroupService.findAllFarmerGroupsWithoutPagination();
        return ResponseEntity.ok().body(farmerGroupListResponseDTOS);
    }

    @GetMapping("/get-role")
    public ResponseEntity<PaginationResponse> findAllFarmGroupsByPenyuluh (@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "role", required = false) String role, @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse farmerGroupListResponseDTOS = farmerGroupService.findAllFarmGroupsByPenyuluh(page, size, sort, search, role, userId);
        return ResponseEntity.ok().body(farmerGroupListResponseDTOS);
    }

    @GetMapping("/poktan-area")
    public ResponseEntity<List<FarmerGroupListResponseDTO>> findAllPoktanByArea (@RequestParam("provinsi") String provinsi, @RequestParam("kabupaten") String kabupaten, @RequestParam("kecamatan") String kecamatan, @RequestParam("desa") String desa) {
        List<FarmerGroupListResponseDTO> farmerGroupListResponseDTOS = farmerGroupService.findAllPoktanByArea(provinsi, kabupaten, kecamatan, desa);
        return ResponseEntity.ok().body(farmerGroupListResponseDTOS);
    }

    @GetMapping("/{poktanId}/farm")
    public ResponseEntity<List<FarmerListResponseDTO>> findAllFarmGroupsWithoutPagination (@PathVariable("poktanId") Long poktanId) {
        List<FarmerListResponseDTO> farmerGroupListResponseDTOS = farmerGroupService.findAllFarmerLinked(poktanId);
        return ResponseEntity.ok().body(farmerGroupListResponseDTOS);
    }

    @GetMapping("/{farmGroupId}")
    public ResponseEntity<FarmerGroupDetailResponseDTO> getDetailFarmerGroup(@PathVariable("farmGroupId") Long farmGroupId) {
        FarmerGroupDetailResponseDTO farmerGroupDetailResponseDTO = farmerGroupService.findFarmerGroup(farmGroupId);
        return ResponseEntity.ok().body(farmerGroupDetailResponseDTO);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PoktanResponse> getDetailFarmerGroupByPoktanId(@RequestParam("poktanId") Long poktanId) {
        PoktanResponse farmerGroupDetailResponseDTO = farmerGroupService.findFarmerGroupByPoktanId(poktanId);
        return ResponseEntity.ok().body(farmerGroupDetailResponseDTO);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<PoktanResponse> getPoktanByUserId(@PathVariable("userId") Long userId) {
        PoktanResponse farmerGroupDetailResponseDTO = farmerGroupService.findFarmerGroupByUserId(userId);
        return ResponseEntity.ok().body(farmerGroupDetailResponseDTO);
    }

    @GetMapping("/list-petani")
    public ResponseEntity<List<Long>> findListPetaniByPoktanUserId(@RequestParam("poktanUserId") Long poktanUserId) {
        List<Long> listPetani = farmerGroupService.findListPetaniByPoktanUserId(poktanUserId);
        return ResponseEntity.ok().body(listPetani);
    }

    @GetMapping("/petani")
    public ResponseEntity<FarmerGroupDetailResponseDTO> getDetailFarmerGroupByPetani(@RequestParam(value = "user", required = false) Long user) {
        FarmerGroupDetailResponseDTO farmerGroupDetailResponseDTO = farmerGroupService.findFarmerGroupByPetani(user);
        return ResponseEntity.ok().body(farmerGroupDetailResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> createFarmerGroup (@RequestBody FarmerGroupCreateRequestDTO farmerGroupCreateRequestDTO) throws URISyntaxException {
        farmerGroupService.createFarmerGroup(farmerGroupCreateRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PostMapping(value = "/user-poktan")
    public ResponseEntity<Void> addFarmerToFarmerGroupByUserPoktan(@RequestParam("farmGroupId") Long farmGroupId, @RequestBody FarmerGroupAddFarmerRequestDTO farmerGroupAddFarmerRequestDTO) throws URISyntaxException {
        farmerGroupService.addFarmerToFarmerGroupByUserPoktan(farmGroupId, farmerGroupAddFarmerRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PostMapping(value = "/{farmGroupId}")
    public ResponseEntity<Void> addFarmerToFarmerGroup(@PathVariable("farmGroupId") Long farmGroupId, @RequestBody FarmerGroupAddFarmerRequestDTO farmerGroupAddFarmerRequestDTO) throws URISyntaxException {
        farmerGroupService.addFarmerToGroup(farmGroupId, farmerGroupAddFarmerRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PutMapping("/{farmGroupId}")
    public ResponseEntity<FarmerGroupDetailResponseDTO> updateFarmerGroup(@PathVariable("farmGroupId") Long farmGroupId, @RequestBody FarmerGroupUpdateRequestDTO farmerGroupUpdateRequestDTO) {
        FarmerGroupDetailResponseDTO farmerGroupDetailResponseDTO = farmerGroupService.updateFarmerGroup(farmGroupId, farmerGroupUpdateRequestDTO);
        return ResponseEntity.ok().body(farmerGroupDetailResponseDTO);
    }

    @DeleteMapping("/{farmGroupId}")
    public ResponseEntity<Void> deleteFarmerGroup (@PathVariable("farmGroupId") Long farmGroupId) {
        farmerGroupService.deleteFarmerGroup(farmGroupId);
        return ResponseEntity.ok().build();
    }
}
