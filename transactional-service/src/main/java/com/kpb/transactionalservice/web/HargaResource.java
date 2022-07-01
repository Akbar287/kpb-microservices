package com.kpb.transactionalservice.web;

import com.kpb.transactionalservice.dto.Harga.HargaRequestDTO;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.service.HargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/transaction/api/harga")
public class HargaResource {

    @Autowired
    private HargaService hargaService;

    @GetMapping("/{produkId}")
    public ResponseEntity<HargaResponseDTO> findDetail(@PathVariable("produkId") Long produkId) {
        HargaResponseDTO hargaResponseDTO = hargaService.findDetail(produkId);
        return ResponseEntity.ok().body(hargaResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody HargaRequestDTO dto) throws URISyntaxException {
        hargaService.create(dto);
        return ResponseEntity.created(new URI("/harga")).build();
    }

    @PutMapping("/{hargaId}")
    public ResponseEntity<Void> update(@PathVariable("hargaId") Long hargaId, @RequestBody HargaRequestDTO dto) {
        hargaService.update(hargaId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hargaId}")
    public ResponseEntity<Void> delete(@PathVariable("hargaId") Long hargaId) {
        hargaService.delete(hargaId);
        return ResponseEntity.noContent().build();
    }

}
