package com.kpb.allocationservice.web;

import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.allocationservice.service.AlokasiPupukSubsidiService;
import com.kpb.clientservice.web.Alokasi.AlokasiPupukSubsidiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/allocation/api/transaction")
public class AlokasiPupukSubsidiResource {
    @Autowired
    private AlokasiPupukSubsidiService alokasiPupukSubsidiService;

    @GetMapping("/{transaksiDetailId}")
    public ResponseEntity<AlokasiPupukSubsidiResponse> getSisaAlokasiPubersNyTransaksi(@PathVariable("transaksiDetailId") Long transaksiDetailId) {
        AlokasiPupukSubsidiResponse alokasiPupukSubsidiResponse = alokasiPupukSubsidiService.getSisaAlokasiPubersNyTransaksi(transaksiDetailId);
        return ResponseEntity.ok().body(alokasiPupukSubsidiResponse);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AlokasiPupukSubsidiCreateUpdateRequestDTO dto) throws URISyntaxException {
        alokasiPupukSubsidiService.create(dto);
        return ResponseEntity.created(new URI("/transaction")).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable("alokasiPupukSubsidiId") Long alokasiPupukSubsidiId, @RequestBody AlokasiPupukSubsidiCreateUpdateRequestDTO dto) {
        alokasiPupukSubsidiService.update(alokasiPupukSubsidiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody AlokasiPupukSubsidiCreateUpdateRequestDTO dto) {
        alokasiPupukSubsidiService.delete(dto);
        return ResponseEntity.noContent().build();
    }
}
