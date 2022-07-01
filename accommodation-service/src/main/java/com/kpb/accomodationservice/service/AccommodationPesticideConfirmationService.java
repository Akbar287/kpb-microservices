package com.kpb.accomodationservice.service;

import com.kpb.accomodationservice.dto.AccommodationPesticideConfirmationRequestDTO;

public interface AccommodationPesticideConfirmationService {
    public void createPesticideConfirmation(Long accommodationPesticideRegistrationId, AccommodationPesticideConfirmationRequestDTO dto);
    public void updatePesticideConfirmation(Long PesticideConfirmationId, AccommodationPesticideConfirmationRequestDTO dto);
    public void deletePesticideConfirmation(Long PesticideConfirmationId);
}
