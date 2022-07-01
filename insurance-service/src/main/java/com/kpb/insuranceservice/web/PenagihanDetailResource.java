package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailDetailResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailListResponseDTO;
import com.kpb.insuranceservice.service.PenagihanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/penagihan-detail")
public class PenagihanDetailResource {
    @Autowired
    private PenagihanDetailService penagihanDetailService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort) {
        PaginationResponse paginationResponse = penagihanDetailService.findAll(page, size, sort);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PenagihanDetailListResponseDTO>> findAllList() {
        List<PenagihanDetailListResponseDTO> penagihanDetailListResponseDTOS = penagihanDetailService.findAllList();
        return ResponseEntity.ok().body(penagihanDetailListResponseDTOS);
    }

    @GetMapping("/{penagihanDetailId}")
    public ResponseEntity<PenagihanDetailDetailResponseDTO> findDetail(@PathVariable("penagihanDetailId") Long penagihanDetailId) {
        PenagihanDetailDetailResponseDTO penagihanDetailDetailResponseDTO = penagihanDetailService.findDetail(penagihanDetailId);
        return ResponseEntity.ok().body(penagihanDetailDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PenagihanDetailCreateUpdateRequestDTO dto) throws URISyntaxException {
        penagihanDetailService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{penagihanDetailId}")
    public ResponseEntity<Void> update(@PathVariable("penagihanDetailId") Long penagihanDetailId, @RequestBody PenagihanDetailCreateUpdateRequestDTO dto) {
        penagihanDetailService.update(penagihanDetailId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{penagihanDetailId}")
    public ResponseEntity<Void> create(@PathVariable("penagihanDetailId") Long penagihanDetailId) {
        penagihanDetailService.delete(penagihanDetailId);
        return ResponseEntity.noContent().build();
    }
}
