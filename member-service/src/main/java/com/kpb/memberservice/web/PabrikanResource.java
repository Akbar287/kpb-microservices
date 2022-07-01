package com.kpb.memberservice.web;

import com.kpb.clientservice.web.Member.PabrikanResponse;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.pabrikan.PabrikanCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanDetailResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanListResponseDTO;
import com.kpb.memberservice.service.PabrikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/members/api/pabrikan")
public class PabrikanResource {

    @Autowired
    private PabrikanService pabrikanService;

    @GetMapping()
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = pabrikanService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PabrikanListResponseDTO>> findAll() {
        List<PabrikanListResponseDTO> dto = pabrikanService.findAllWithoutPagination();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/list-kios")
    public ResponseEntity<List<Long>> findListKiosByPabrikanUserId(@RequestParam("pabrikanUserId") Long pabrikanUserId) {
        List<Long> dto = pabrikanService.findListKiosByPabrikanUserId(pabrikanUserId);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{pabrikanId}")
    public ResponseEntity<PabrikanDetailResponseDTO> findDetail(@PathVariable("pabrikanId") Long pabrikanId) {
        PabrikanDetailResponseDTO pabrikanDetailResponseDTO = pabrikanService.findDetail(pabrikanId);
        return ResponseEntity.ok().body(pabrikanDetailResponseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<PabrikanResponse> findDetailByUserId(@RequestParam("pabrikanUserId") Long pabrikanUserId) {
        PabrikanResponse pabrikanDetailResponseDTO = pabrikanService.findDetailByUserId(pabrikanUserId);
        return ResponseEntity.ok().body(pabrikanDetailResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PabrikanCreateUpdateRequestDTO dto) throws URISyntaxException {
        pabrikanService.create(dto);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PutMapping("/{pabrikanId}")
    public ResponseEntity<Void> update(@PathVariable("pabrikanId") Long pabrikanId, @RequestBody PabrikanCreateUpdateRequestDTO dto) {
        pabrikanService.update(pabrikanId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pabrikanId}")
    public ResponseEntity<Void> delete(@PathVariable("pabrikanId") Long pabrikanId) {
        pabrikanService.delete(pabrikanId);
        return ResponseEntity.noContent().build();
    }
}
