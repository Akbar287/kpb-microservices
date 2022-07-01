package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiListResponseDTO;
import com.kpb.insuranceservice.service.PelaksanaAsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/pelaksana-asuransi")
public class PelaksanaAsuransiResource {
    @Autowired
    private PelaksanaAsuransiService pelaksanaAsuransiService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = pelaksanaAsuransiService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PelaksanaAsuransiListResponseDTO>> findAllList() {
        List<PelaksanaAsuransiListResponseDTO> pelaksanaAsuransiListResponseDTOS = pelaksanaAsuransiService.findAllList();
        return ResponseEntity.ok().body(pelaksanaAsuransiListResponseDTOS);
    }

    @GetMapping("/{pelaksanaAsuransiId}")
    public ResponseEntity<PelaksanaAsuransiDetailResponseDTO> findDetail(@PathVariable("pelaksanaAsuransiId") Long pelaksanaAsuransiId) {
        PelaksanaAsuransiDetailResponseDTO pelaksanaAsuransiDetailResponseDTO = pelaksanaAsuransiService.findDetail(pelaksanaAsuransiId);
        return ResponseEntity.ok().body(pelaksanaAsuransiDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PelaksanaAsuransiCreateUpdateRequestDTO dto) throws URISyntaxException {
        pelaksanaAsuransiService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{pelaksanaAsuransiId}")
    public ResponseEntity<Void> update(@PathVariable("pelaksanaAsuransiId") Long pelaksanaAsuransiId, @RequestBody PelaksanaAsuransiCreateUpdateRequestDTO dto) {
        pelaksanaAsuransiService.update(pelaksanaAsuransiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pelaksanaAsuransiId}")
    public ResponseEntity<Void> create(@PathVariable("pelaksanaAsuransiId") Long pelaksanaAsuransiId) {
        pelaksanaAsuransiService.delete(pelaksanaAsuransiId);
        return ResponseEntity.noContent().build();
    }
}
