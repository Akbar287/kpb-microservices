package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailListResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailResponseDTO;
import com.kpb.insuranceservice.service.PenagihanBankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/penagihan-bank")
public class PenagihanBankDetailResource {
    @Autowired
    private PenagihanBankDetailService penagihanBankDetailService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = penagihanBankDetailService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PenagihanBankDetailListResponseDTO>> findAllList() {
        List<PenagihanBankDetailListResponseDTO> penagihanBankDetailListResponseDTOS = penagihanBankDetailService.findAllList();
        return ResponseEntity.ok().body(penagihanBankDetailListResponseDTOS);
    }

    @GetMapping("/{penagihanBankDetailId}")
    public ResponseEntity<PenagihanBankDetailResponseDTO> findDetail(@PathVariable("penagihanBankDetailId") Long penagihanBankDetailId) {
        PenagihanBankDetailResponseDTO penagihanBankDetailResponseDTO = penagihanBankDetailService.findDetail(penagihanBankDetailId);
        return ResponseEntity.ok().body(penagihanBankDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PenagihanBankDetailCreateUpdateRequestDTO dto) throws URISyntaxException {
        penagihanBankDetailService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{penagihanBankDetailId}")
    public ResponseEntity<Void> update(@PathVariable("penagihanBankDetailId") Long penagihanBankDetailId, @RequestBody PenagihanBankDetailCreateUpdateRequestDTO dto) {
        penagihanBankDetailService.update(penagihanBankDetailId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{penagihanBankDetailId}")
    public ResponseEntity<Void> create(@PathVariable("penagihanBankDetailId") Long penagihanBankDetailId) {
        penagihanBankDetailService.delete(penagihanBankDetailId);
        return ResponseEntity.noContent().build();
    }
}
