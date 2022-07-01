package com.kpb.memberservice.web;

import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.distributor.*;
import com.kpb.memberservice.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/members/api/distributor")
public class DistributorResource {

    @Autowired
    private DistributorService distributorService;

    @GetMapping()
    public ResponseEntity<PaginationResponse> findAllDistributor(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "role", required = false, defaultValue = "all") String role, @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse distributorListResponseDTOS = distributorService.findAllDistributor(page, size, sort, search, role, userId);
        return ResponseEntity.ok().body(distributorListResponseDTOS);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DistributorListResponseDTO>> findAllDistributor() {
        List<DistributorListResponseDTO> distributorListResponseDTOS = distributorService.findAllDistributorWithoutPagination();
        return ResponseEntity.ok().body(distributorListResponseDTOS);
    }

    @GetMapping("/list-kios")
    public ResponseEntity<List<Long>> findAllKiosIdByDistributorUserId(@RequestParam("distributorUserId") Long distributorUserId) {
        List<Long> distributorListResponseDTOS = distributorService.findAllKiosIdByDistributorUserId(distributorUserId);
        return ResponseEntity.ok().body(distributorListResponseDTOS);
    }

    @GetMapping("/kios")
    public ResponseEntity<DistributorResponse> findDetailDistributorByKiosFromUser(@RequestParam("user") Long user) {
        DistributorResponse distributorDetailResponseDTO = distributorService.findDetailDistributorByKiosFromUser(user);
        return ResponseEntity.ok().body(distributorDetailResponseDTO);
    }
    @GetMapping("/petani")
    public ResponseEntity<DistributorResponse> findDetailDistributorByPetaniFromUser(@RequestParam("user") Long user) {
        DistributorResponse distributorDetailResponseDTO = distributorService.findDetailDistributorByPetaniFromUser(user);
        return ResponseEntity.ok().body(distributorDetailResponseDTO);
    }
    @GetMapping("/poktan")
    public ResponseEntity<DistributorResponse> findDetailDistributorByPoktanFromUser(@RequestParam("user") Long user) {
        DistributorResponse distributorDetailResponseDTO = distributorService.findDetailDistributorByPoktanFromUser(user);
        return ResponseEntity.ok().body(distributorDetailResponseDTO);
    }

    @GetMapping("/{distributorId}")
    public ResponseEntity<DistributorDetailResponseDTO> findDetailDistributor(@PathVariable("distributorId") Long distributorId) {
        DistributorDetailResponseDTO distributorDetailResponseDTO = distributorService.findDetailDistributor(distributorId);
        return ResponseEntity.ok().body(distributorDetailResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> createDistributor(@RequestBody DistributorCreateRequestDTO distributorCreateRequestDTO) throws URISyntaxException {
        distributorService.createDistributor(distributorCreateRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PutMapping("/{distributorId}")
    public ResponseEntity<Void> updateDistributor(@PathVariable("distributorId") Long distributorId, @RequestBody DistributorUpdateRequestDTO dto) {
        distributorService.updateDistributor(distributorId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{distributorId}/pabrikan/{pabrikanId}")
    public ResponseEntity<Void> updatePabrikan(@PathVariable("distributorId") Long distributorId, @PathVariable("pabrikanId") Long pabrikanId) {
        distributorService.updatePabrikan(distributorId, pabrikanId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{distributorId}/working")
    public ResponseEntity<Void> updateDistributorWorkingArea(@PathVariable("distributorId") Long distributorId, List<DistributorWorkingAreaCreateUpdateDTO> distributorWorkingAreaCreateUpdateDTOS) throws URISyntaxException {
        distributorService.updateDistributorWorkingArea(distributorId, distributorWorkingAreaCreateUpdateDTOS);
        return ResponseEntity.created(new URI("/working")).build();
    }


    @DeleteMapping("/{distributorId}")
    public ResponseEntity<Void> deleteDistributor(@PathVariable("distributorId") Long distributorId) {
        distributorService.deleteDistributor(distributorId);
        return ResponseEntity.noContent().build();
    }
}
