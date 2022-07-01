package com.kpb.authservice.web;

import com.kpb.authservice.dto.area.AreaDetailResponseDTO;
import com.kpb.authservice.dto.area.AreaListCreateUpdateDTO;
import com.kpb.authservice.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/auth/api/area")
@Slf4j
public class AreaResource {

    @Autowired
    private AreaService areaService;

    @GetMapping("/get-area")
    public ResponseEntity<List<Long>> getAreaByForm(@RequestParam("provinsi") String provinsi, @RequestParam("kabupaten") String kabupaten, @RequestParam("kecamatan") String kecamatan, @RequestParam("desa") String desa) {
        List<Long> dto = areaService.getUserIdInAreaByForm(provinsi, kabupaten, kecamatan, desa);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AreaDetailResponseDTO>> areaDetailResponseDTOResponseEntity(@PathVariable("userId") Long userId) {
        List<AreaDetailResponseDTO> dto = areaService.findDetail(userId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<AreaDetailResponseDTO> createArea(@PathVariable("userId") Long userId, @RequestBody AreaListCreateUpdateDTO dto) throws URISyntaxException {
        AreaDetailResponseDTO areaDetailResponseDTO =  areaService.createArea(userId, dto);
        return ResponseEntity.created(new URI("/area")).body(areaDetailResponseDTO);
    }

    @PutMapping("/{areaId}")
    public ResponseEntity<Void> updateArea(@PathVariable("areaId") Long areaId, @RequestBody AreaListCreateUpdateDTO dto) {
        areaService.updateArea(areaId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{areaId}")
    public ResponseEntity<Void> createArea(@PathVariable("areaId") Long areaId) {
        areaService.deleteArea(areaId);
        return ResponseEntity.noContent().build();
    }
}
