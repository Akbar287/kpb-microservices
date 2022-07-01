package com.kpb.accomodationservice.service;

import com.kpb.accomodationservice.dto.AccommodationPesticideDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationPesticideListResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationPesticideRegistrationCreateUpdateDTO;

import java.util.List;

public interface AccommodationPesticideRegistrationService {
    public List<AccommodationPesticideListResponseDTO> findAllPesticideRegistration();
    public AccommodationPesticideDetailResponseDTO findDetailPesticideRegistration(Long accommodationPesticideId);
    public void createPesticideRegistration(AccommodationPesticideRegistrationCreateUpdateDTO dto);
    public void updatePesticideRegistration(Long accommodationPesticideId, AccommodationPesticideRegistrationCreateUpdateDTO dto);
    public void deletePesticideRegistration(Long accommodationPesticideId);
}
