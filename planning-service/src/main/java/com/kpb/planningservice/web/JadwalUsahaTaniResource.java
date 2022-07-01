package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniRequestDTO;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniResponseDTO;
import com.kpb.planningservice.service.JadwalUsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/jadwal")
public class JadwalUsahaTaniResource {

    @Autowired
    private JadwalUsahaTaniService jadwalUsahaTaniService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<JadwalUsahaTaniResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<JadwalUsahaTaniResponseDTO> dto = jadwalUsahaTaniService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JadwalUsahaTaniRequestDTO dto) throws URISyntaxException {
        jadwalUsahaTaniService.create(dto);
        return ResponseEntity.created(new URI("/jadwal")).build();
    }

    @PutMapping("/{jadwalUsahaTaniId}")
    public ResponseEntity<Void> update(@PathVariable("jadwalUsahaTaniId") Long jadwalUsahaTaniId, @RequestBody JadwalUsahaTaniRequestDTO dto) {
        jadwalUsahaTaniService.update(jadwalUsahaTaniId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jadwalUsahaTaniId}")
    public ResponseEntity<Void> delete(@PathVariable("jadwalUsahaTaniId") Long jadwalUsahaTaniId) {
        jadwalUsahaTaniService.delete(jadwalUsahaTaniId);
        return ResponseEntity.noContent().build();
    }

}
