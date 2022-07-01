package com.kpb.notificationservice.web;

import com.kpb.notificationservice.dto.Notifikasi.NotifikasiRequestDTO;
import com.kpb.notificationservice.dto.Notifikasi.NotifikasiResponseDTO;
import com.kpb.notificationservice.dto.PaginationResponse;
import com.kpb.notificationservice.service.NotifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/notifikasi/api/notifikasi")
public class NotifikasiResource {

    @Autowired
    private NotifikasiService notifikasiService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "0") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse paginationResponse = notifikasiService.findAll(page, size, sort, search, userId);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{notifikasiId}")
    public ResponseEntity<NotifikasiResponseDTO> findDetail(@PathVariable("notifikasiId") Long notifikasiId) {
        NotifikasiResponseDTO klaimDetailResponseDTO = notifikasiService.findDetail(notifikasiId);
        return ResponseEntity.ok().body(klaimDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody NotifikasiRequestDTO dto) throws URISyntaxException {
        notifikasiService.create(dto);
        return ResponseEntity.created(new URI("/notifikasi")).build();
    }

    @PutMapping("/{notifikasiId}")
    public ResponseEntity<Void> update(@PathVariable("notifikasiId") Long notifikasiId, @RequestBody NotifikasiRequestDTO dto) {
        notifikasiService.update(notifikasiId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{notifikasiId}")
    public ResponseEntity<Void> delete(@PathVariable("notifikasiId") Long notifikasiId) {
        notifikasiService.delete(notifikasiId);
        return ResponseEntity.noContent().build();
    }
}
