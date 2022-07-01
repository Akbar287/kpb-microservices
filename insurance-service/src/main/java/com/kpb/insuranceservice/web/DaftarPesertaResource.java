package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaDetailResponseDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.DaftarPesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/insurance/api/daftar-peserta")
public class DaftarPesertaResource {
    @Autowired
    private DaftarPesertaService daftarPesertaService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "tahun", required = false) Integer tahun, @RequestParam(value = "masa_tanam", required = false) Integer masa_tanam) {
        PaginationResponse paginationResponse = daftarPesertaService.findAll(page, size, sort, tahun, masa_tanam);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DaftarPesertaListResponseDTO>> findAllList() {
        List<DaftarPesertaListResponseDTO> daftarPesertaListResponseDTOS = daftarPesertaService.findAllList();
        return ResponseEntity.ok().body(daftarPesertaListResponseDTOS);
    }

    @GetMapping("/{daftarPesertaId}")
    public ResponseEntity<DaftarPesertaDetailResponseDTO> findDetail(@PathVariable("daftarPesertaId") Long daftarPesertaId) {
        DaftarPesertaDetailResponseDTO daftarPesertaDetailResponseDTO = daftarPesertaService.findDetail(daftarPesertaId);
        return ResponseEntity.ok().body(daftarPesertaDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DaftarPesertaCreateUpdateRequestDTO dto) throws URISyntaxException {
        daftarPesertaService.create(dto);
        return ResponseEntity.created(new URI("/daftar-peserta")).build();
    }

    @PutMapping("/{daftarPesertaId}")
    public ResponseEntity<Void> update(@PathVariable("daftarPesertaId") Long daftarPesertaId, @RequestBody DaftarPesertaCreateUpdateRequestDTO dto) {
        daftarPesertaService.update(daftarPesertaId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{daftarPesertaId}")
    public ResponseEntity<Void> create(@PathVariable("daftarPesertaId") Long daftarPesertaId) {
        daftarPesertaService.delete(daftarPesertaId);
        return ResponseEntity.noContent().build();
    }
}
