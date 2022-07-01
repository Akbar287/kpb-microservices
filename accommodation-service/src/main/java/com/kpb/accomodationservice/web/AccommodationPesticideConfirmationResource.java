package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationPesticideConfirmationRequestDTO;
import com.kpb.accomodationservice.service.AccommodationPesticideConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/accommodation/api/confirmation")
public class AccommodationPesticideConfirmationResource {

    @Autowired
    private AccommodationPesticideConfirmationService accommodationPesticideConfirmationService;

    @PostMapping("/{accommodationPesticideRegistrationId}")
    public ResponseEntity<Void> createPesticideConfirmation(@PathVariable("accommodationPesticideRegistrationId") Long accommodationPesticideRegistrationId,@RequestBody AccommodationPesticideConfirmationRequestDTO dto) throws URISyntaxException {
        accommodationPesticideConfirmationService.createPesticideConfirmation(accommodationPesticideRegistrationId, dto);
        return ResponseEntity.created(new URI("/confirmation")).build();
    }

    @PutMapping("/{PesticideConfirmationId}")
    public ResponseEntity<Void> updatePesticideConfirmation(@PathVariable("PesticideConfirmationId") Long PesticideConfirmationId, @RequestBody AccommodationPesticideConfirmationRequestDTO dto) {
        accommodationPesticideConfirmationService.updatePesticideConfirmation(PesticideConfirmationId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{PesticideConfirmationId}")
    public ResponseEntity<Void> deletePesticideConfirmation(@PathVariable("PesticideConfirmationId") Long PesticideConfirmationId) {
        accommodationPesticideConfirmationService.deletePesticideConfirmation(PesticideConfirmationId);
        return ResponseEntity.ok().build();
    }
}
