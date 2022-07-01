package com.kpb.memberservice.web;

import com.kpb.clientservice.web.Member.KomoditasSubsektorResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.*;
import com.kpb.memberservice.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/members/api/farmer")
public class FarmerResource {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public ResponseEntity<PaginationResponse> farmerListResponseDTOResponseEntity (@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "kios", required = false) Long kios) {
        PaginationResponse farmerListResponseDTOS = farmerService.findAllFarmer(page, size, sort, search, kios);
        return ResponseEntity.ok().body(farmerListResponseDTOS);
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<FarmerDetailResponseDTO> farmerDetailResponseDTOResponseEntity (@PathVariable("farmerId") Long farmerId) {
        FarmerDetailResponseDTO farmerDetailResponseDTO = farmerService.findFarmerDetail(farmerId);
        return ResponseEntity.ok().body(farmerDetailResponseDTO);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<FarmerDetailResponseDTO> farmerDetailResponseDTOByUser (@PathVariable("userId") Long userId) {
        FarmerDetailResponseDTO farmerDetailResponseDTO = farmerService.farmerDetailResponseDTOByUser(userId);
        return ResponseEntity.ok().body(farmerDetailResponseDTO);
    }

    @GetMapping("/cek-nik")
    public ResponseEntity<Boolean> checkNik (@RequestParam("nik") String nik) {
        Boolean booleanNik = farmerService.checkNik(nik);
        return ResponseEntity.ok().body(booleanNik);
    }

    @GetMapping("/komoditas")
    public ResponseEntity<KomoditasSubsektorResponse> findKomoditasAndSubsektor(@RequestParam("petaniId") Long petaniId) {
        KomoditasSubsektorResponse komoditas = farmerService.findKomoditasAndSubsektor(petaniId);
        return ResponseEntity.ok().body(komoditas);
    }

    @GetMapping("/search-nik")
    public ResponseEntity<List<Long>> searchNik (@RequestParam("nik") String nik) {
        List<Long> listPetaniId = farmerService.searchNik(nik);
        return ResponseEntity.ok().body(listPetaniId);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PaginationResponse> farmerDetailResponseDTOByPoktan (@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse farmerDetailResponseDTO = farmerService.farmerDetailResponseDTOByPoktan(page, size, sort, search, userId);
        return ResponseEntity.ok().body(farmerDetailResponseDTO);
    }

    @GetMapping("/get-role")
    public ResponseEntity<PaginationResponse> farmerDetailResponseDTOByAllRole (@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "role", required = false) String role, @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse farmerDetailResponseDTO = farmerService.farmerDetailResponseDTOByAllRole(page, size, sort, search, role, userId);
        return ResponseEntity.ok().body(farmerDetailResponseDTO);
    }

    @PutMapping("/{farmerId}")
    public ResponseEntity<FarmerDetailResponseDTO> updateFarmer (@PathVariable("farmerId") Long farmerId, @RequestBody FarmerUpdateRequestDTO farmerUpdateRequestDTO) {
        FarmerDetailResponseDTO responseDTO = farmerService.updateFarmer(farmerId, farmerUpdateRequestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/{farmerId}/asset")
    public ResponseEntity<Void> updateFarmerAsset(@PathVariable("farmerId") Long farmerId, @RequestBody List<FarmerAssetCreateUpdateRequestDTO> farmerAssetCreateUpdateRequestDTO) {
        farmerService.updateAssetFarmer(farmerId, farmerAssetCreateUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{farmerId}/ownership")
    public ResponseEntity<Void> updateFarmerOwnership(@PathVariable("farmerId") Long farmerId, @RequestBody FarmerOwnershipCreateUpdateRequestDTO farmerOwnershipCreateUpdateRequestDTO) {
        farmerService.updateOwnershipFarmer(farmerId, farmerOwnershipCreateUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{petaniId}/surface")
    public ResponseEntity<Void> updateFarmerSurface(@PathVariable("petaniId") Long petaniId, @RequestBody FarmerSurfaceAreaCreateUpdateRequestDTO farmerSurfaceAreaCreateUpdateRequestDTOS) throws URISyntaxException {
        farmerService.updateSurfaceAreaFarmer(petaniId, farmerSurfaceAreaCreateUpdateRequestDTOS);
        if(farmerSurfaceAreaCreateUpdateRequestDTOS.getLuasLahanId() != 0) return ResponseEntity.created(new URI("/surface")).build();
        if(farmerSurfaceAreaCreateUpdateRequestDTOS.getLuasLahanId() < 0) return ResponseEntity.noContent().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{farmerId}/family")
    public ResponseEntity<Void> updateFarmerFamily(@PathVariable("farmerId") Long farmerId, @RequestBody FarmerFamilyCreateUpdateRequestDTO farmerFamilyCreateUpdateRequestDTO) {
        farmerService.updateFamilyFarmer(farmerId, farmerFamilyCreateUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/{farmerId}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable("farmerId") Long farmerId) {
        farmerService.deleteFarmer(farmerId);
        return ResponseEntity.ok().build();
    }
}
