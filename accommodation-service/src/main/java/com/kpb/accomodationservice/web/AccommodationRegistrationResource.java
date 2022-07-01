package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationRegistrationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.dto.AccommodationRegistrationDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationRegistrationListResponse;
import com.kpb.accomodationservice.service.AccommodationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/accommodation/api/registration")
public class AccommodationRegistrationResource {

    @Autowired
    private AccommodationRegistrationService accommodationRegistrationService;

    @GetMapping("/{accommodationId}")
    public ResponseEntity<List<AccommodationRegistrationListResponse>> findAllRegistration(@PathVariable("accommodationId") Long accommodationId) {
        List<AccommodationRegistrationListResponse> accommodationRegistrationListResponses = accommodationRegistrationService.accommodationRegistrationListResponse(accommodationId);
        return ResponseEntity.ok().body(accommodationRegistrationListResponses);
    }

    @GetMapping("/{accommodationRegistrationId}/detail")
    public ResponseEntity<AccommodationRegistrationDetailResponseDTO> findDetailRegistration(@PathVariable("accommodationRegistrationId") Long accommodationRegistrationId) {
        AccommodationRegistrationDetailResponseDTO accommodationRegistrationDetailResponseDTO = accommodationRegistrationService.accommodationRegistrationDetailResponseDTO(accommodationRegistrationId);
        return ResponseEntity.ok().body(accommodationRegistrationDetailResponseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createRegistration(@RequestBody AccommodationRegistrationCreateUpdateRequestDTO accommodationRegistrationCreateUpdateRequestDTO) throws URISyntaxException {
        accommodationRegistrationService.createAccommodationRegistration(accommodationRegistrationCreateUpdateRequestDTO);
        return ResponseEntity.created(new URI("/registration")).build();
    }

    @PutMapping("/{accommodationRegistrationId}")
    public ResponseEntity<Void> updateRegistration(@PathVariable("accommodationRegistrationId") Long accommodationRegistrationId,@RequestBody AccommodationRegistrationCreateUpdateRequestDTO accommodationRegistrationCreateUpdateRequestDTO) {
        accommodationRegistrationService.updateAccommodationRegistration(accommodationRegistrationId, accommodationRegistrationCreateUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accommodationRegistrationId}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable("accommodationRegistrationId") Long accommodationRegistrationId) {
        accommodationRegistrationService.deleteAccommodationRegistration(accommodationRegistrationId);
        return ResponseEntity.noContent().build();
    }
}
