package com.kpb.transactionalservice.web;

import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiCreateRequestDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiDetailResponseDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiUpdateRequestDTO;
import com.kpb.transactionalservice.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/transaction/api/transaksi")
public class TransaksiResource {

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "userId", required = false) Long userId, @RequestParam(value = "jenis", required = false, defaultValue = "all") String jenis, @RequestParam(value = "kiosUserId", required = false) Long kiosUserId) {
        PaginationResponse paginationResponse = transaksiService.findAll(page, size,sort, search, userId, jenis, kiosUserId);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PaginationResponse> findAllToPoktan(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "poktanUserId", required = false) Long poktanUserId, @RequestParam(value = "jenis", required = false, defaultValue = "all") String jenis) {
        PaginationResponse paginationResponse = transaksiService.findAllToPoktan(page, size,sort, search, poktanUserId, jenis);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/role-get")
    public ResponseEntity<PaginationResponse> findAllToAllRole(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "userId", required = false) Long userId, @RequestParam(value = "jenis", required = false, defaultValue = "all") String jenis, @RequestParam(value = "role", required = false, defaultValue = "all") String role) {
        PaginationResponse paginationResponse = transaksiService.findAllToAllRole(page, size,sort, search, userId, jenis, role);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{transaksiId}")
    public ResponseEntity<TransaksiDetailResponseDTO> findDetail(@PathVariable("transaksiId") Long transaksiId) {
        TransaksiDetailResponseDTO transaksiDetailResponseDTO = transaksiService.findDetail(transaksiId);
        return ResponseEntity.ok().body(transaksiDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransaksiCreateRequestDTO dto) throws URISyntaxException {
        transaksiService.create(dto);
        return ResponseEntity.created(new URI("/harga")).build();
    }

    @PutMapping("/{transaksiId}")
    public ResponseEntity<Void> update(@PathVariable("transaksiId") Long transaksiId, @RequestBody TransaksiUpdateRequestDTO dto) {
        transaksiService.update(transaksiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{transaksiId}")
    public ResponseEntity<Void> delete(@PathVariable("transaksiId") Long transaksiId) {
        transaksiService.delete(transaksiId);
        return ResponseEntity.noContent().build();
    }

}
