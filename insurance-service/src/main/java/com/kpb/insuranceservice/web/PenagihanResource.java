package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanDetailResponseDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanListResponseDTO;
import com.kpb.insuranceservice.service.PenagihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/penagihan")
public class PenagihanResource {
    @Autowired
    private PenagihanService penagihanService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = penagihanService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PenagihanListResponseDTO>> findAllList() {
        List<PenagihanListResponseDTO> penagihanListResponseDTOS = penagihanService.findAllList();
        return ResponseEntity.ok().body(penagihanListResponseDTOS);
    }

    @GetMapping("/{penagihanId}")
    public ResponseEntity<PenagihanDetailResponseDTO> findDetail(@PathVariable("penagihanId") Long penagihanId) {
        PenagihanDetailResponseDTO penagihanDetailResponseDTO = penagihanService.findDetail(penagihanId);
        return ResponseEntity.ok().body(penagihanDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PenagihanCreateUpdateRequestDTO dto) throws URISyntaxException {
        penagihanService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{penagihanId}")
    public ResponseEntity<Void> update(@PathVariable("penagihanId") Long penagihanId, @RequestBody PenagihanCreateUpdateRequestDTO dto) {
        penagihanService.update(penagihanId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{penagihanId}")
    public ResponseEntity<Void> create(@PathVariable("penagihanId") Long penagihanId) {
        penagihanService.delete(penagihanId);
        return ResponseEntity.noContent().build();
    }
}
