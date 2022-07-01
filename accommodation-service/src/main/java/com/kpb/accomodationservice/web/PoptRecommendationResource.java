package com.kpb.accomodationservice.web;

import com.kpb.accomodationservice.dto.AccommodationPoptRecommendationDTO;
import com.kpb.accomodationservice.service.AccommodationPesticidePoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accommodation/api/recommendation")
public class PoptRecommendationResource {

    @Autowired
    private AccommodationPesticidePoptService accommodationPesticidePoptService;

    @PostMapping("/{accommodationPesticideRegistrationId}")
    public ResponseEntity<Void> createPoptRecommendation(@PathVariable("accommodationPesticideRegistrationId") Long accommodationPesticideRegistrationId, AccommodationPoptRecommendationDTO dto) {
        accommodationPesticidePoptService.createPoptRecommendation(accommodationPesticideRegistrationId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{PoptRecommendationId}")
    public ResponseEntity<Void> updatePoptRecommendation(@PathVariable("PoptRecommendationId") Long PoptRecommendationId, AccommodationPoptRecommendationDTO dto) {
        accommodationPesticidePoptService.updatePoptRecommendation(PoptRecommendationId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{PoptRecommendationId}")
    public ResponseEntity<Void> deletePoptRecommendation(@PathVariable("PoptRecommendationId") Long PoptRecommendationId) {
        accommodationPesticidePoptService.deletePoptRecommendation(PoptRecommendationId);
        return ResponseEntity.ok().build();
    }
}
