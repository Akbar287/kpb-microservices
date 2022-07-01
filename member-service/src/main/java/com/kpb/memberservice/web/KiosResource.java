package com.kpb.memberservice.web;

import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.kios.KiosCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.dto.kios.KiosResponseDTO;
import com.kpb.memberservice.dto.kios.KiosWorkingAreaCreateUpdateDTO;
import com.kpb.memberservice.service.KiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/members/api/kios")
public class KiosResource {

    @Autowired
    private KiosService kiosService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "userId", required = false) Long userId, @RequestParam(value = "role", required = false, defaultValue = "all") String role) {
        PaginationResponse paginationResponse = kiosService.findAll(page, size, sort, search, userId, role);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<KiosListResponseDTO>> findAll(@RequestParam(value = "distributor", required = false) Long distributor) {
        List<KiosListResponseDTO> kiosListResponseDTOS = kiosService.findAllWithoutPagination(distributor);
        return ResponseEntity.ok().body(kiosListResponseDTOS);
    }

    @GetMapping("/{kiosId}")
    public ResponseEntity<KiosResponseDTO> findDetail(@PathVariable("kiosId") Long kiosId) {
        KiosResponseDTO kiosResponseDTO = kiosService.findDetail(kiosId);
        return ResponseEntity.ok().body(kiosResponseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<KiosResponseDTO> findKiosDetailByUser(@RequestParam("user") Long user) {
        KiosResponseDTO kiosResponseDTO = kiosService.findKiosDetailByUser(user);
        return ResponseEntity.ok().body(kiosResponseDTO);
    }

    @GetMapping("/penyuluh")
    public ResponseEntity<List<KiosListResponseDTO>> findKiosListByPenyuluh(@RequestParam(value = "penyuluhUserId", required = false) Long penyuluhUserId) {
        List<KiosListResponseDTO> kiosResponseDTO = kiosService.findKiosListByPenyuluh(penyuluhUserId);
        return ResponseEntity.ok().body(kiosResponseDTO);
    }

    @GetMapping("/petani")
    public ResponseEntity<KiosResponseDTO> findDetailByUser(@RequestParam("user") Long user) {
        KiosResponseDTO kiosResponseDTO = kiosService.findDetailByUser(user);
        return ResponseEntity.ok().body(kiosResponseDTO);
    }

    @GetMapping("/poktan")
    public ResponseEntity<KiosResponseDTO> findDetailByPoktanUser(@RequestParam("user") Long user) {
        KiosResponseDTO kiosResponseDTO = kiosService.findDetailByPoktanUser(user);
        return ResponseEntity.ok().body(kiosResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody KiosCreateUpdateRequestDTO dto) throws URISyntaxException {
        kiosService.create(dto);
        return ResponseEntity.created(new URI("/kios")).build();
    }

    @PutMapping("/{kiosId}/working")
    public ResponseEntity<Void> updateWorking(@PathVariable("kiosId") Long kiosId, @RequestBody List<KiosWorkingAreaCreateUpdateDTO> dto) {
        kiosService.updateWorkingArea(kiosId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{kiosId}/distributor")
    public ResponseEntity<Void> updateDistributor(@PathVariable("kiosId") Long kiosId, @PathVariable("distributorId") Long distributorId) {
        kiosService.updateDistributor(kiosId, distributorId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{kiosId}")
    public ResponseEntity<Void> update(@PathVariable("kiosId") Long kiosId, @RequestBody KiosCreateUpdateRequestDTO dto) {
        kiosService.update(kiosId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{kiosId}")
    public ResponseEntity<Void> delete(@PathVariable("kiosId") Long kiosId) {
        kiosService.delete(kiosId);
        return ResponseEntity.noContent().build();
    }
}
