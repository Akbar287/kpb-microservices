package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanRequestDTO;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanResponseDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniDetailResponseDTO;
import com.kpb.planningservice.service.JenisTanamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/jenis")
public class JenisTanamanResource {

    @Autowired
    private JenisTanamanService jenisTanamanService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<JenisTanamanResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<JenisTanamanResponseDTO> dto = jenisTanamanService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JenisTanamanRequestDTO dto) throws URISyntaxException {
        jenisTanamanService.create(dto);
        return ResponseEntity.created(new URI("/jenis")).build();
    }

    @PutMapping("/{jenisTanamanId}")
    public ResponseEntity<Void> update(@PathVariable("jenisTanamanId") Long jenisTanamanId, @RequestBody JenisTanamanRequestDTO dto) {
        jenisTanamanService.update(jenisTanamanId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{jenisTanamanId}")
    public ResponseEntity<Void> delete(@PathVariable("jenisTanamanId") Long jenisTanamanId) {
        jenisTanamanService.delete(jenisTanamanId);
        return ResponseEntity.noContent().build();
    }
}
