package com.kpb.memberservice.web;

import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.penyuluh.PenyuluhCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.penyuluh.PenyuluhListResponseDTO;
import com.kpb.memberservice.service.PenyuluhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/members/api/penyuluh")
public class PenyuluhResource {
    @Autowired
    private PenyuluhService penyuluhService;

    @GetMapping()
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse pagination = penyuluhService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(pagination);
    }

    @GetMapping("/list-kios")
    public ResponseEntity<List<Long>> getListKiosId(@RequestParam("penyuluhUserId") Long penyuluhUserId) {
        List<Long> penyuluhListResponseDTO = penyuluhService.getListKiosId(penyuluhUserId);
        return ResponseEntity.ok().body(penyuluhListResponseDTO);
    }

    @GetMapping("/{penyuluhId}")
    public ResponseEntity<PenyuluhListResponseDTO> findDetail(@PathVariable("penyuluhId") Long penyuluhId) {
        PenyuluhListResponseDTO penyuluhListResponseDTO = penyuluhService.findDetail(penyuluhId);
        return ResponseEntity.ok().body(penyuluhListResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PenyuluhCreateUpdateRequestDTO penyuluhCreateUpdateRequestDTO) throws URISyntaxException {
        penyuluhService.create(penyuluhCreateUpdateRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PutMapping("/{penyuluhId}")
    public ResponseEntity<Void> update(@PathVariable("penyuluhId") Long penyuluhId, @RequestBody PenyuluhCreateUpdateRequestDTO dto) {
        penyuluhService.update(penyuluhId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{penyuluhId}/kios/{kiosId}")
    public ResponseEntity<Void> updateKios(@PathVariable("penyuluhId") Long penyuluhId, @PathVariable("kiosId") Long kiosId) {
        penyuluhService.updateKios(penyuluhId, kiosId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{penyuluhId}")
    public ResponseEntity<Void> delete(@PathVariable("penyuluhId") Long penyuluhId) {
        penyuluhService.delete(penyuluhId);
        return ResponseEntity.noContent().build();
    }
}
