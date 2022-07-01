package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.dto.AccommodationDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationListResponseDTO;
import com.kpb.accomodationservice.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/accommodation/api/accommodation")
public class AccommodationResource {

    @Autowired
    private AccommodationService accommodationService;

    @GetMapping("/")
    public ResponseEntity<List<AccommodationListResponseDTO>> findAllAccommodation() {
        List<AccommodationListResponseDTO> accommodationListResponseDTO = accommodationService.accommodationListResponseDTOS();
        return ResponseEntity.ok().body(accommodationListResponseDTO);
    }

    @GetMapping("/{accommodationId}")
    public ResponseEntity<AccommodationDetailResponseDTO> findAccommodation(@PathVariable("accommodationId") Long accommodationId) {
        AccommodationDetailResponseDTO dto = accommodationService.accommodationDetailResponseDTO(accommodationId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createAccommodation(@RequestBody AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO) throws URISyntaxException {
        accommodationService.createAccommodation(accommodationCreateUpdateRequestDTO);
        return ResponseEntity.created(new URI("/")).build();
    }

    @PutMapping("/{accommodationId}")
    public ResponseEntity<Void> updateAccommodation(@PathVariable("accommodationId") Long accommodationId, @RequestBody AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO) {
        accommodationService.updateAccommodation(accommodationId, accommodationCreateUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accommodationId}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable("accommodationId") Long accommodationId) {
        accommodationService.deleteAccommodation(accommodationId);
        return ResponseEntity.noContent().build();
    }
}
