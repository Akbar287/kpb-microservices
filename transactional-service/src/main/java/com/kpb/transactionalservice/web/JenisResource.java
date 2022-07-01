package com.kpb.transactionalservice.web;

import com.kpb.transactionalservice.dto.Jenis.JenisRequestDTO;
import com.kpb.transactionalservice.dto.Jenis.JenisResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.service.JenisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/transaction/api/jenis")
public class JenisResource {

    @Autowired
    private JenisService jenisService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = jenisService.findAll(page, size,sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{jenisId}")
    public ResponseEntity<JenisResponseDTO> findDetail(@PathVariable("jenisId") Long jenisId) {
        JenisResponseDTO jenisResponseDTO = jenisService.findDetail(jenisId);
        return ResponseEntity.ok().body(jenisResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JenisRequestDTO dto) throws URISyntaxException {
        jenisService.create(dto);
        return ResponseEntity.created(new URI("/jenis")).build();
    }

    @PutMapping("/{jenisId}")
    public ResponseEntity<Void> update(@PathVariable("jenisId") Long jenisId, @RequestBody JenisRequestDTO dto) {
        jenisService.update(jenisId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jenisId}")
    public ResponseEntity<Void> delete(@PathVariable("jenisId") Long jenisId) {
        jenisService.delete(jenisId);
        return ResponseEntity.noContent().build();
    }

}
