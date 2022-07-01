package com.kpb.insuranceservice.web;

import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimListResponseDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.service.FileKlaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/insurance/api/file")
@RestController
public class FileKlaimResource {

    @Autowired
    private FileKlaimService fileKlaimService;

    @GetMapping()
    public ResponseEntity<List<FileKlaimListResponseDTO>> findAllList(@RequestParam("klaimId") Long klaimId) {
        List<FileKlaimListResponseDTO> klaimListResponseDTOS = fileKlaimService.findAll(klaimId);
        return ResponseEntity.ok().body(klaimListResponseDTOS);
    }

    @GetMapping("/{fileKlaimId}")
    public ResponseEntity<FileKlaimDetailResponseDTO> findDetail(@PathVariable("fileKlaimId") Long fileKlaimId) {
        FileKlaimDetailResponseDTO klaimDetailResponseDTO = fileKlaimService.findDetail(fileKlaimId);
        return ResponseEntity.ok().body(klaimDetailResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FileKlaimCreateUpdateRequestDTO dto) throws URISyntaxException {
        fileKlaimService.create(dto);
        return ResponseEntity.created(new URI("/file-klaim")).build();
    }

    @PutMapping("/{fileKlaimId}")
    public ResponseEntity<Void> update(@PathVariable("fileKlaimId") Long fileKlaimId, @RequestBody FileKlaimCreateUpdateRequestDTO dto) {
        fileKlaimService.update(fileKlaimId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{fileKlaimId}")
    public ResponseEntity<Void> delete(@PathVariable("fileKlaimId") Long fileKlaimId) {
        fileKlaimService.delete(fileKlaimId);
        return ResponseEntity.noContent().build();
    }
}
