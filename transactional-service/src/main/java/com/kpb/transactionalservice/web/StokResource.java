package com.kpb.transactionalservice.web;

import com.kpb.transactionalservice.dto.Stok.StokRequestDTO;
import com.kpb.transactionalservice.dto.Stok.StokResponseDTO;
import com.kpb.transactionalservice.service.StokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/transaction/api/stok")
public class StokResource {

    @Autowired
    private StokService stokService;

    @GetMapping("/{productId}")
    public ResponseEntity<StokResponseDTO> findDetail(@PathVariable("productId") Long productId, @RequestParam("distributor")Long distributor, @RequestParam("kios") Long kios, @RequestParam("tahun") int tahun, @RequestParam("bulan") String bulan) {
        StokResponseDTO stokResponseDTO = stokService.findDetail(productId, distributor, kios, tahun, bulan);
        return ResponseEntity.ok().body(stokResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody StokRequestDTO dto) throws URISyntaxException {
        stokService.create(dto);
        return ResponseEntity.created(new URI("/stok")).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody StokRequestDTO dto) {
        stokService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{stokId}")
    public ResponseEntity<Void> delete(@PathVariable("stokId") Long stokId) {
        stokService.delete(stokId);
        return ResponseEntity.noContent().build();
    }

}
