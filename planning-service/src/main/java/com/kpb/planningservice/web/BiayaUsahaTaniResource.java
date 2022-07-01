package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniRequestDTO;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniResponseDTO;
import com.kpb.planningservice.service.BiayaUsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/biaya")
public class BiayaUsahaTaniResource {

    @Autowired
    private BiayaUsahaTaniService biayaUsahaTaniService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<BiayaTaniResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<BiayaTaniResponseDTO> dto = biayaUsahaTaniService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody BiayaTaniRequestDTO dto) throws URISyntaxException {
        biayaUsahaTaniService.create(dto);
        return ResponseEntity.created(new URI("/biaya")).build();
    }

    @PutMapping("/{biayaUsahaTaniId}")
    public ResponseEntity<Void> update(@PathVariable("biayaUsahaTaniId") Long biayaUsahaTaniId, @RequestBody BiayaTaniRequestDTO dto) {
        biayaUsahaTaniService.update(biayaUsahaTaniId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{biayaUsahaTaniId}")
    public ResponseEntity<Void> delete(@PathVariable("biayaUsahaTaniId") Long biayaUsahaTaniId) {
        biayaUsahaTaniService.delete(biayaUsahaTaniId);
        return ResponseEntity.noContent().build();
    }

}
