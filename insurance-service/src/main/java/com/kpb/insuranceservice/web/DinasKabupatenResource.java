package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.DinasKabupatenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/kabupaten")
public class DinasKabupatenResource {

    @Autowired
    private DinasKabupatenService dinasKabupatenService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = dinasKabupatenService.findAll(page, size, sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DinasKabupatenListResponseDTO>> findAllList() {
        List<DinasKabupatenListResponseDTO> dinasKabupatenListResponseDTOS = dinasKabupatenService.findAllList();
        return ResponseEntity.ok().body(dinasKabupatenListResponseDTOS);
    }

    @GetMapping("/{dinasKabupatenId}")
    public ResponseEntity<DinasKabupatenDetailResponseDTO> findDetail(@PathVariable("dinasKabupatenId") Long dinasKabupatenId) {
        DinasKabupatenDetailResponseDTO dinasKabupatenDetailResponseDTO = dinasKabupatenService.findDetail(dinasKabupatenId);
        return ResponseEntity.ok().body(dinasKabupatenDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DinasKabupatenCreateUpdateRequestDTO dto) throws URISyntaxException {
        dinasKabupatenService.create(dto);
        return ResponseEntity.created(new URI("/asuransi")).build();
    }

    @PutMapping("/{dinasKabupatenId}")
    public ResponseEntity<Void> update(@PathVariable("dinasKabupatenId") Long dinasKabupatenId, @RequestBody DinasKabupatenCreateUpdateRequestDTO dto) {
        dinasKabupatenService.update(dinasKabupatenId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dinasKabupatenId}")
    public ResponseEntity<Void> create(@PathVariable("dinasKabupatenId") Long dinasKabupatenId) {
        dinasKabupatenService.delete(dinasKabupatenId);
        return ResponseEntity.noContent().build();
    }
}
