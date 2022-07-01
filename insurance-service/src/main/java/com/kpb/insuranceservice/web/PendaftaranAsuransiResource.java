package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiListResponseDTO;
import com.kpb.insuranceservice.service.PendaftaranAsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/pendaftaran")
public class PendaftaranAsuransiResource {
    @Autowired
    private PendaftaranAsuransiService pendaftaranAsuransiService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort,@RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = pendaftaranAsuransiService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PendaftaranAsuransiListResponseDTO>> findAllList() {
        List<PendaftaranAsuransiListResponseDTO> pendaftaranAsuransiListResponseDTOS = pendaftaranAsuransiService.findAllList();
        return ResponseEntity.ok().body(pendaftaranAsuransiListResponseDTOS);
    }

    @GetMapping("/{pendaftaranAsuransiId}")
    public ResponseEntity<PendaftaranAsuransiDetailResponseDTO> findDetail(@PathVariable("pendaftaranAsuransiId") Long pendaftaranAsuransiId) {
        PendaftaranAsuransiDetailResponseDTO pendaftaranAsuransiDetailResponseDTO = pendaftaranAsuransiService.findDetail(pendaftaranAsuransiId);
        return ResponseEntity.ok().body(pendaftaranAsuransiDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PendaftaranAsuransiCreateUpdateRequestDTO dto) throws URISyntaxException {
        pendaftaranAsuransiService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{pendaftaranAsuransiId}")
    public ResponseEntity<Void> update(@PathVariable("pendaftaranAsuransiId") Long pendaftaranAsuransiId, @RequestBody PendaftaranAsuransiCreateUpdateRequestDTO dto) {
        pendaftaranAsuransiService.update(pendaftaranAsuransiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pendaftaranAsuransiId}")
    public ResponseEntity<Void> create(@PathVariable("pendaftaranAsuransiId") Long pendaftaranAsuransiId) {
        pendaftaranAsuransiService.delete(pendaftaranAsuransiId);
        return ResponseEntity.noContent().build();
    }
}
