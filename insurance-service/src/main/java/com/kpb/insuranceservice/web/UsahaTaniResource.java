package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniDetailResponseDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniListResponseDTO;
import com.kpb.insuranceservice.service.UsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/usaha-tani")
public class UsahaTaniResource {

    @Autowired
    private UsahaTaniService usahaTaniService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = usahaTaniService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsahaTaniListResponseDTO>> findAllList() {
        List<UsahaTaniListResponseDTO> usahaTaniListResponseDTOS = usahaTaniService.findAllList();
        return ResponseEntity.ok().body(usahaTaniListResponseDTOS);
    }

    @GetMapping("/{usahaTaniId}")
    public ResponseEntity<UsahaTaniDetailResponseDTO> findDetail(@PathVariable("usahaTaniId") Long usahaTaniId) {
        UsahaTaniDetailResponseDTO usahaTaniDetailResponseDTO = usahaTaniService.findDetail(usahaTaniId);
        return ResponseEntity.ok().body(usahaTaniDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UsahaTaniCreateUpdateRequestDTO dto) throws URISyntaxException {
        usahaTaniService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{usahaTaniId}")
    public ResponseEntity<Void> update(@PathVariable("usahaTaniId") Long usahaTaniId, @RequestBody UsahaTaniCreateUpdateRequestDTO dto) {
        usahaTaniService.update(usahaTaniId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{usahaTaniId}")
    public ResponseEntity<Void> create(@PathVariable("usahaTaniId") Long usahaTaniId) {
        usahaTaniService.delete(usahaTaniId);
        return ResponseEntity.noContent().build();
    }
}
