package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanRequestDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanResponseDTO;
import com.kpb.planningservice.service.KebutuhanSaprotanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/kebutuhan")
public class KebutuhanSaprotanResource {

    @Autowired
    private KebutuhanSaprotanService kebutuhanSaprotanService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<KebutuhanSaprotanResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<KebutuhanSaprotanResponseDTO> dto = kebutuhanSaprotanService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KebutuhanSaprotanRequestDTO dto) throws URISyntaxException {
        kebutuhanSaprotanService.create( dto);
        return ResponseEntity.created(new URI("/kebutuhan")).build();
    }

    @PutMapping("/{kebutuhanSaprotanId}")
    public ResponseEntity<Void> update(@PathVariable("kebutuhanSaprotanId") Long kebutuhanSaprotanId, @RequestBody KebutuhanSaprotanRequestDTO dto) {
        kebutuhanSaprotanService.update(kebutuhanSaprotanId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{kebutuhanSaprotanId}")
    public ResponseEntity<Void> delete(@PathVariable("kebutuhanSaprotanId") Long kebutuhanSaprotanId) {
        kebutuhanSaprotanService.delete(kebutuhanSaprotanId);
        return ResponseEntity.noContent().build();
    }
}
