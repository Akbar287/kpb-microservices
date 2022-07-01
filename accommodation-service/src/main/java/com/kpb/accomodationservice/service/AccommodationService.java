package com.kpb.accomodationservice.service;

import com.kpb.accomodationservice.dto.AccommodationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.dto.AccommodationDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationListResponseDTO;

import java.util.List;

public interface AccommodationService {
    public List<AccommodationListResponseDTO> accommodationListResponseDTOS();
    public AccommodationDetailResponseDTO accommodationDetailResponseDTO(Long accommodationId);
    public void createAccommodation(AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO);
    public void updateAccommodation(Long accommodationId ,AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO);
    public void deleteAccommodation(Long accommodationId);
}
