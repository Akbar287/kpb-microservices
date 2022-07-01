package com.kpb.transactionalservice.web;

import com.kpb.transactionalservice.dto.Harga.HargaRequestDTO;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriRequestDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.service.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/transaction/api/kategori")
public class KategoriResource {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse paginationResponse = kategoriService.findAll(page, size,sort, search);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{kategoriId}")
    public ResponseEntity<KategoriResponseDTO> findDetail(@PathVariable("kategoriId") Long kategoriId) {
        KategoriResponseDTO kategoriResponseDTO = kategoriService.findDetail(kategoriId);
        return ResponseEntity.ok().body(kategoriResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KategoriRequestDTO dto) throws URISyntaxException {
        kategoriService.create(dto);
        return ResponseEntity.created(new URI("/kategori")).build();
    }

    @PutMapping("/{kategoriId}")
    public ResponseEntity<Void> update(@PathVariable("kategoriId") Long kategoriId, @RequestBody KategoriRequestDTO dto) {
        kategoriService.update(kategoriId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{kategoriId}")
    public ResponseEntity<Void> delete(@PathVariable("kategoriId") Long kategoriId) {
        kategoriService.delete(kategoriId);
        return ResponseEntity.noContent().build();
    }

}
