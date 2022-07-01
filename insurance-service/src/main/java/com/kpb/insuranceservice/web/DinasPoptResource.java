package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.DinasPoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/popt")
public class DinasPoptResource {
    @Autowired
    private DinasPoptService dinasPoptService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = dinasPoptService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DinasPoptListResponseDTO>> findAllList() {
        List<DinasPoptListResponseDTO> dinasPoptListResponseDTOS = dinasPoptService.findAllList();
        return ResponseEntity.ok().body(dinasPoptListResponseDTOS);
    }

    @GetMapping("/{dinasPoptId}")
    public ResponseEntity<DinasPoptDetailResponseDTO> findDetail(@PathVariable("dinasPoptId") Long dinasPoptId) {
        DinasPoptDetailResponseDTO dinasPoptDetailResponseDTO = dinasPoptService.findDetail(dinasPoptId);
        return ResponseEntity.ok().body(dinasPoptDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DinasPoptCreateUpdateRequestDTO dto) throws URISyntaxException {
        dinasPoptService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{dinasPoptId}")
    public ResponseEntity<Void> update(@PathVariable("dinasPoptId") Long dinasPoptId, @RequestBody DinasPoptCreateUpdateRequestDTO dto) {
        dinasPoptService.update(dinasPoptId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dinasPoptId}")
    public ResponseEntity<Void> create(@PathVariable("dinasPoptId") Long dinasPoptId) {
        dinasPoptService.delete(dinasPoptId);
        return ResponseEntity.noContent().build();
    }
}
