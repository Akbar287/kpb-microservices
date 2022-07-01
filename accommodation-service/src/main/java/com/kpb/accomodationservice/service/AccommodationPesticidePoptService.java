package com.kpb.accomodationservice.service;

import com.kpb.accomodationservice.dto.AccommodationPoptRecommendationDTO;

public interface AccommodationPesticidePoptService {
    public void createPoptRecommendation(Long accommodationPesticideRegistrationId,AccommodationPoptRecommendationDTO dto);
    public void updatePoptRecommendation(Long PoptRecommendationId, AccommodationPoptRecommendationDTO dto);
    public void deletePoptRecommendation(Long PoptRecommendationId);
}
