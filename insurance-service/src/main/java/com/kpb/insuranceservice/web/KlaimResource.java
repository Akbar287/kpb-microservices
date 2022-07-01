package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.Klaim.KlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.KlaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/klaim")
public class KlaimResource {
    @Autowired
    private KlaimService klaimService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = klaimService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<KlaimListResponseDTO>> findAllList() {
        List<KlaimListResponseDTO> klaimListResponseDTOS = klaimService.findAllList();
        return ResponseEntity.ok().body(klaimListResponseDTOS);
    }

    @GetMapping("/{klaimId}")
    public ResponseEntity<KlaimDetailResponseDTO> findDetail(@PathVariable("klaimId") Long klaimId) {
        KlaimDetailResponseDTO klaimDetailResponseDTO = klaimService.findDetail(klaimId);
        return ResponseEntity.ok().body(klaimDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KlaimCreateUpdateRequestDTO dto) throws URISyntaxException {
        klaimService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{klaimId}")
    public ResponseEntity<Void> update(@PathVariable("klaimId") Long klaimId, @RequestBody KlaimCreateUpdateRequestDTO dto) {
        klaimService.update(klaimId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{klaimId}")
    public ResponseEntity<Void> delete(@PathVariable("klaimId") Long klaimId) {
        klaimService.delete(klaimId);
        return ResponseEntity.noContent().build();
    }
}
