package com.kpb.allocationservice.web;

import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.allocationservice.dto.PupukSubsidi.*;
import com.kpb.allocationservice.service.PupukSubsidiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/allocation/api/pubers")
public class PupukSubsidiResource {

    @Autowired
    private PupukSubsidiService pupukSubsidiService;

    @GetMapping("/sisa")
    public ResponseEntity<SisaPupukSubsidiResponseDTO> findSisaPupuk(@RequestParam("petani") Long petani, @RequestParam("tahun") int tahun, @RequestParam("masa_tanam") int masa_tanam, @RequestParam(value = "pupuk", required = false) String pupuk) {
        SisaPupukSubsidiResponseDTO sisaPupukSubsidiResponseDTO = pupukSubsidiService.findSisaPupuk(petani, tahun, masa_tanam, pupuk);
        return ResponseEntity.ok().body(sisaPupukSubsidiResponseDTO);
    }

    @GetMapping("/alokasi-tph")
    public ResponseEntity<InformasiAlokasiPupukSubsidiPoktanResponse> alokasiPupukOPDTPH(@RequestParam("poktanUserId") Long poktanUserId, @RequestParam("tahun") int tahun) {
        InformasiAlokasiPupukSubsidiPoktanResponse sisaPupukSubsidiResponseDTO = pupukSubsidiService.alokasiPupukOPDTPH(poktanUserId, tahun);
        return ResponseEntity.ok().body(sisaPupukSubsidiResponseDTO);
    }

    @GetMapping("/penebusan")
    public ResponseEntity<PupukSubsidiDetailResponseDTO> findAlokasiDetailByPetaniAndYear(@RequestParam(value = "petani", required = false) Long petani,@RequestParam(value = "userId", required = false) Long userId, @RequestParam("tahun") int tahun, @RequestParam("masa_tanam") int masa_tanam) {
        PupukSubsidiDetailResponseDTO sisaPupukSubsidiResponseDTO = pupukSubsidiService.findAlokasiDetailByPetaniAndYear(petani, userId, tahun, masa_tanam);
        return ResponseEntity.ok().body(sisaPupukSubsidiResponseDTO);
    }

    @GetMapping("/poktan-penebusan")
    public ResponseEntity<List<PupukSubsidiDetailResponseDTO>> findAlokasiDetailByPoktanAndYear(@RequestParam(value = "userId", required = false) Long userId, @RequestParam("tahun") int tahun, @RequestParam("masa_tanam") int masa_tanam) {
        List<PupukSubsidiDetailResponseDTO> sisaPupukSubsidiResponseDTO = pupukSubsidiService.findAlokasiDetailByPoktanAndYear(userId, tahun, masa_tanam);
        return ResponseEntity.ok().body(sisaPupukSubsidiResponseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<PaginationResponse> findAllList(@RequestParam(value = "petani", required = false) Long petani, @RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "user", required = false) Long user) {
        PaginationResponse pupukSubsidiListResponseDTOS = pupukSubsidiService.findAllList(petani, page, size, user);
        return ResponseEntity.ok().body(pupukSubsidiListResponseDTOS);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PaginationResponse> findAllListPoktan(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "user", required = false) Long user, @RequestParam(value = "tahun", required = false) Integer tahun) {
        PaginationResponse pupukSubsidiListResponseDTOS = pupukSubsidiService.findAllListPoktan(page, size, user, tahun);
        return ResponseEntity.ok().body(pupukSubsidiListResponseDTOS);
    }

    @GetMapping("/get-role")
    public ResponseEntity<PaginationResponse> findAllListbyAllRole(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "role", required = false) String role, @RequestParam(value = "user", required = false) Long user, @RequestParam(value = "tahun", required = false) int tahun) {
        PaginationResponse pupukSubsidiListResponseDTOS = pupukSubsidiService.findAllListbyAllRole(page, size, role, user, tahun);
        return ResponseEntity.ok().body(pupukSubsidiListResponseDTOS);
    }

    @GetMapping("/binatani")
    public ResponseEntity<PaginationResponse> findAllListBinatani(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "user", required = false) Long user) {
        PaginationResponse pupukSubsidiListResponseDTOS = pupukSubsidiService.findAllListBinatani(page, size, user);
        return ResponseEntity.ok().body(pupukSubsidiListResponseDTOS);
    }

    @GetMapping("/penyuluh")
    public ResponseEntity<PaginationResponse> findAllListPenyuluh(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "user", required = false) Long user) {
        PaginationResponse pupukSubsidiListResponseDTOS = pupukSubsidiService.findAllListPenyuluh(page, size, user);
        return ResponseEntity.ok().body(pupukSubsidiListResponseDTOS);
    }

    @GetMapping("/{pupukSubsidiId}")
    public ResponseEntity<PupukSubsidiDetailResponseDTO> findDetail(@PathVariable("pupukSubsidiId") Long pupukSubsidiId) {
        PupukSubsidiDetailResponseDTO pupukSubsidiDetailResponseDTO = pupukSubsidiService.findDetail(pupukSubsidiId);
        return ResponseEntity.ok().body(pupukSubsidiDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PupukSubsidiCreateUpdateRequestDTO dto) throws URISyntaxException {
        pupukSubsidiService.create(dto);
        return ResponseEntity.created(new URI("/pubers")).build();
    }

    @PutMapping("/{pupukSubsidiId}")
    public ResponseEntity<Void> update(@PathVariable("pupukSubsidiId") Long pupukSubsidiId, @RequestBody PupukSubsidiCreateUpdateRequestDTO dto) {
        pupukSubsidiService.update(pupukSubsidiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pupukSubsidiId}")
    public ResponseEntity<Void> delete(@PathVariable("pupukSubsidiId") Long pupukSubsidiId) {
        pupukSubsidiService.delete(pupukSubsidiId);
        return ResponseEntity.noContent().build();
    }
}
