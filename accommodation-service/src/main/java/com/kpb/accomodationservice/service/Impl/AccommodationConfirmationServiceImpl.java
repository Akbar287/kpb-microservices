package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.AccommodationConfirmation;
import com.kpb.accomodationservice.domain.AccommodationRegistration;
import com.kpb.accomodationservice.dto.AccommodationConfirmationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.AccommodationConfirmationRepository;
import com.kpb.accomodationservice.repository.AccommodationRegistrationRepository;
import com.kpb.accomodationservice.service.AccommodationConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccommodationConfirmationServiceImpl implements AccommodationConfirmationService {

    @Autowired
    private AccommodationRegistrationRepository accommodationRegistrationRepository;

    @Autowired
    private AccommodationConfirmationRepository accommodationConfirmationRepository;


    @Override
    public void createAccommodationConfirmation(Long accommodationRegistrationId, AccommodationConfirmationCreateUpdateRequestDTO dto) {
        AccommodationRegistration accommodationRegistration = accommodationRegistrationRepository.findById(accommodationRegistrationId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));

        AccommodationConfirmation accommodationConfirmation = new AccommodationConfirmation();
        accommodationConfirmation.setConfirmation_by(dto.getConfirmationBy());
        accommodationConfirmation.setConfirmation_type(dto.getConfirmationType());
        accommodationConfirmation.setConfirmation_date(LocalDate.now());
        accommodationConfirmation.setDescription(dto.getDesccription());
        accommodationConfirmation.setCreated_at(LocalDate.now());
        accommodationConfirmation.setAccommodationRegistration(accommodationRegistration);
        accommodationConfirmationRepository.save(accommodationConfirmation);
    }

    @Override
    public void updateAccommodationConfirmation(Long accommodationConfirmationId, AccommodationConfirmationCreateUpdateRequestDTO dto) {
        AccommodationConfirmation accommodationConfirmation = accommodationConfirmationRepository.findById(accommodationConfirmationId).orElseThrow(()->new ResourceNotFoundException("confirmation.not.found"));
        accommodationConfirmation.setConfirmation_by(dto.getConfirmationBy());
        accommodationConfirmation.setConfirmation_type(dto.getConfirmationType());
        accommodationConfirmation.setConfirmation_date(LocalDate.now());
        accommodationConfirmation.setDescription(dto.getDesccription());
        accommodationConfirmationRepository.save(accommodationConfirmation);
    }

    @Override
    public void deleteAccommodationConfirmation(Long accommodationConfirmationId) {
        accommodationConfirmationRepository.deleteById(accommodationConfirmationId);
    }
}
