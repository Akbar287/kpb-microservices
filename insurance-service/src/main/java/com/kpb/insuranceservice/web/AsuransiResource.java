package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.Asuransi.AsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.AsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/asuransi")
public class AsuransiResource {

    @Autowired
    private AsuransiService asuransiService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = asuransiService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<PaginationResponse> findAllList(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse asuransiListResponseDTOS = asuransiService.findAllList(page, size, sort, search);
        return ResponseEntity.ok().body(asuransiListResponseDTOS);
    }

    @GetMapping("/{asuransiId}")
    public ResponseEntity<AsuransiDetailResponseDTO> findDetail(@PathVariable("asuransiId") Long asuransiId) {
        AsuransiDetailResponseDTO asuransiDetailResponseDTO = asuransiService.findDetail(asuransiId);
        return ResponseEntity.ok().body(asuransiDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AsuransiCreateUpdateRequestDTO dto) throws URISyntaxException {
        asuransiService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{asuransiId}")
    public ResponseEntity<Void> update(@PathVariable("asuransiId") Long asuransiId, @RequestBody AsuransiCreateUpdateRequestDTO dto) {
        asuransiService.update(asuransiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{asuransiId}")
    public ResponseEntity<Void> create(@PathVariable("asuransiId") Long asuransiId) {
        asuransiService.delete(asuransiId);
        return ResponseEntity.noContent().build();
    }
}
