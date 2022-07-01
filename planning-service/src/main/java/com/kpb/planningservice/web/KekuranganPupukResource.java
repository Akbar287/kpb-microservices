package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukResponseDTO;
import com.kpb.planningservice.service.KekuranganPupukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/kekurangan")
public class KekuranganPupukResource {

    @Autowired
    private KekuranganPupukService kekuranganPupukService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<KekuranganPupukResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<KekuranganPupukResponseDTO> dto = kekuranganPupukService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KekuranganPupukCreateUpdateRequestDTO dto) throws URISyntaxException {
        kekuranganPupukService.create( dto);
        return ResponseEntity.created(new URI("/kekurangan")).build();
    }

    @PutMapping("/{kekuranganPupukId}")
    public ResponseEntity<Void> update(@PathVariable("kekuranganPupukId") Long kekuranganPupukId, @RequestBody KekuranganPupukCreateUpdateRequestDTO dto) {
        kekuranganPupukService.update(kekuranganPupukId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{kekuranganPupukId}")
    public ResponseEntity<Void> delete(@PathVariable("kekuranganPupukId") Long kekuranganPupukId) {
        kekuranganPupukService.delete(kekuranganPupukId);
        return ResponseEntity.noContent().build();
    }
}
