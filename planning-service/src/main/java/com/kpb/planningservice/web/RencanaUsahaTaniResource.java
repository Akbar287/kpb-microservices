package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.PaginationResponse;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniDetailResponseDTO;
import com.kpb.planningservice.service.RencanaUsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/planning/api/rencana")
public class RencanaUsahaTaniResource {

    @Autowired
    private RencanaUsahaTaniService rencanaUsahaTaniService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "petani", required = false) Long petani, @RequestParam(value = "tahun", required = false) int tahun, @RequestParam(value = "user", required = false) Long user) {
        PaginationResponse paginationResponse = rencanaUsahaTaniService.findAll(page, size, sort, search, petani, tahun,user);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/luas-lahan")
    public ResponseEntity<Integer> findLuasLahan(@RequestParam(value = "petaniId", required = false) Long petaniId) {
        Integer i = rencanaUsahaTaniService.findLuasLahan(petaniId);
        return ResponseEntity.ok().body(i);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PaginationResponse> findAllForPoktan(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "poktanUserId", required = false) Long poktanUserId, @RequestParam(value = "tahun", required = false) int tahun) {
        PaginationResponse paginationResponse = rencanaUsahaTaniService.findAllPoktan(page, size, sort, search, poktanUserId, tahun);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<RencanaUsahaTaniDetailResponseDTO> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        RencanaUsahaTaniDetailResponseDTO dto = rencanaUsahaTaniService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RencanaUsahaTaniCreateUpdateRequestDTO dto) throws URISyntaxException {
        rencanaUsahaTaniService.create(dto);
        return ResponseEntity.created(new URI("/rencana")).build();
    }

    @PutMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<Void> update(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId, @RequestBody RencanaUsahaTaniCreateUpdateRequestDTO dto) {
        rencanaUsahaTaniService.update(rencanaUsahaTaniId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<Void> delete(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        rencanaUsahaTaniService.delete(rencanaUsahaTaniId);
        return ResponseEntity.noContent().build();
    }
}
