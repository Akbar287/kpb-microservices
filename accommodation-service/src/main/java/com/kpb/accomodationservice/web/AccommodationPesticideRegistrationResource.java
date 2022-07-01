package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationPesticideDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationPesticideListResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationPesticideRegistrationCreateUpdateDTO;
import com.kpb.accomodationservice.service.AccommodationPesticideRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/accommodation/api/pesticide")
public class AccommodationPesticideRegistrationResource {
    @Autowired
    private AccommodationPesticideRegistrationService accommodationPesticideRegistrationService;

    @GetMapping("/")
    public ResponseEntity<List<AccommodationPesticideListResponseDTO>> findAllPesticideRegistration() {
        List<AccommodationPesticideListResponseDTO> accommodationPesticideListResponseDTOS = accommodationPesticideRegistrationService.findAllPesticideRegistration();
        return ResponseEntity.ok().body(accommodationPesticideListResponseDTOS);
    }

    @GetMapping("/{accommodationPesticideId}")
    public ResponseEntity<AccommodationPesticideDetailResponseDTO> findDetailPesticideRegistration(@PathVariable("accommodationPesticideId") Long accommodationPesticideId) {
        AccommodationPesticideDetailResponseDTO accommodationPesticideDetailResponseDTO = accommodationPesticideRegistrationService.findDetailPesticideRegistration(accommodationPesticideId);
        return ResponseEntity.ok().body(accommodationPesticideDetailResponseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createPesticideRegistration(@RequestBody AccommodationPesticideRegistrationCreateUpdateDTO dto) throws URISyntaxException {
        accommodationPesticideRegistrationService.createPesticideRegistration(dto);
        return ResponseEntity.created(new URI("/pesticide")).build();
    }

    @PutMapping("/{accommodationPesticideId}")
    public ResponseEntity<Void> updatePesticideRegistration(@PathVariable("accommodationPesticideId") Long accommodationPesticideId, @RequestBody AccommodationPesticideRegistrationCreateUpdateDTO dto) {
        accommodationPesticideRegistrationService.updatePesticideRegistration(accommodationPesticideId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accommodationPesticideId}")
    public ResponseEntity<Void> deletePesticideRegistration(@PathVariable("accommodationPesticideId") Long accommodationPesticideId) {
        accommodationPesticideRegistrationService.deletePesticideRegistration(accommodationPesticideId);
        return ResponseEntity.noContent().build();
    }
}
