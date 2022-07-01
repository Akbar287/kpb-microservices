package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationConfirmationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.service.AccommodationConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/accommodation/api/confirmation")
public class AccommodationConfirmationResource {

    @Autowired
    private AccommodationConfirmationService accommodationConfirmationService;

    @PostMapping("/{accommodationRegistrationId}")
    public ResponseEntity<Void> createConfirmation(@PathVariable("accommodationRegistrationId") Long accommodationRegistrationId, @RequestBody AccommodationConfirmationCreateUpdateRequestDTO dto) throws URISyntaxException {
        accommodationConfirmationService.createAccommodationConfirmation(accommodationRegistrationId, dto);
        return ResponseEntity.created(new URI(("/"))).build();
    }

    @PutMapping("/{accommodationConfirmationId}")
    public ResponseEntity<Void> updateConfirmation(@PathVariable("accommodationConfirmationId") Long accommodationConfirmationId, @RequestBody AccommodationConfirmationCreateUpdateRequestDTO dto) {
        accommodationConfirmationService.updateAccommodationConfirmation(accommodationConfirmationId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{accommodationConfirmationId}")
    public ResponseEntity<Void> deleteConfirmation(@PathVariable("accommodationConfirmationId") Long accommodationConfirmationId) {
        accommodationConfirmationService.deleteAccommodationConfirmation(accommodationConfirmationId);
        return ResponseEntity.noContent().build();
    }
}
