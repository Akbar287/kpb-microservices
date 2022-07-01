package com.kpb.accomodationservice.service;

import com.kpb.accomodationservice.dto.AccommodationConfirmationCreateUpdateRequestDTO;

public interface AccommodationConfirmationService {
    public void createAccommodationConfirmation(Long accommodationRegistrationId, AccommodationConfirmationCreateUpdateRequestDTO dto);
    public void updateAccommodationConfirmation(Long accommodationConfirmationId, AccommodationConfirmationCreateUpdateRequestDTO dto);
    public void deleteAccommodationConfirmation(Long accommodationConfirmationId);
}
