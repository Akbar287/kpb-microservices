package com.kpb.planningservice.web;

import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiResponseDTO;
import com.kpb.planningservice.service.RealisasiPupukSubsidiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/planning/api/realisasi")
public class RealisasiPupukSubsidiResource {

    @Autowired
    private RealisasiPupukSubsidiService realisasiPupukSubsidiService;

    @GetMapping("/{rencanaUsahaTaniId}")
    public ResponseEntity<List<RealisasiPupukSubsidiResponseDTO>> findDetail(@PathVariable("rencanaUsahaTaniId") Long rencanaUsahaTaniId) {
        List<RealisasiPupukSubsidiResponseDTO> dto = realisasiPupukSubsidiService.findDetail(rencanaUsahaTaniId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create( @RequestBody RealisasiPupukSubsidiCreateUpdateRequestDTO dto) throws URISyntaxException {
        realisasiPupukSubsidiService.create(dto);
        return ResponseEntity.created(new URI("/realisasi")).build();
    }

    @PutMapping("/{realisasiPupukSubsidiId}")
    public ResponseEntity<Void> update(@PathVariable("realisasiPupukSubsidiId") Long realisasiPupukSubsidiId, @RequestBody RealisasiPupukSubsidiCreateUpdateRequestDTO dto) {
        realisasiPupukSubsidiService.update(realisasiPupukSubsidiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{realisasiPupukSubsidiId}")
    public ResponseEntity<Void> delete(@PathVariable("realisasiPupukSubsidiId") Long realisasiPupukSubsidiId) {
        realisasiPupukSubsidiService.delete(realisasiPupukSubsidiId);
        return ResponseEntity.noContent().build();
    }
}
