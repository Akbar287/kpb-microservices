package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.AccommodationPesticideConfirmation;
import com.kpb.accomodationservice.domain.AccommodationPesticideRegistration;
import com.kpb.accomodationservice.dto.AccommodationPesticideConfirmationRequestDTO;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.AccommodationPesticideConfirmationRepository;
import com.kpb.accomodationservice.repository.AccommodationPesticideRegistrationRepository;
import com.kpb.accomodationservice.service.AccommodationPesticideConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccommodationPesticideConfirmationServiceImpl implements AccommodationPesticideConfirmationService {

    @Autowired
    private AccommodationPesticideConfirmationRepository accommodationPesticideConfirmationRepository;

    @Autowired
    private AccommodationPesticideRegistrationRepository accommodationPesticideRegistrationRepository;

    @Override
    public void createPesticideConfirmation(Long accommodationPesticideRegistrationId, AccommodationPesticideConfirmationRequestDTO dto) {
        AccommodationPesticideRegistration accommodationPesticideRegistration = accommodationPesticideRegistrationRepository.findById(accommodationPesticideRegistrationId).orElseThrow(()->new ResourceNotFoundException("resource.not.found"));

        AccommodationPesticideConfirmation accommodationPesticideConfirmation = new AccommodationPesticideConfirmation();
        accommodationPesticideConfirmation.setConfirmation_by(dto.getConfirmationBy());
        accommodationPesticideConfirmation.setConfirmation_date(LocalDate.now());
        accommodationPesticideConfirmation.setConfirmation_type(dto.getConfirmationType());
        accommodationPesticideConfirmation.setDescription(dto.getDescription());
        accommodationPesticideConfirmation.setCreated_at(LocalDate.now());
        accommodationPesticideConfirmation.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
        accommodationPesticideConfirmationRepository.save(accommodationPesticideConfirmation);
    }

    @Override
    public void updatePesticideConfirmation(Long PesticideConfirmationId, AccommodationPesticideConfirmationRequestDTO dto) {
        AccommodationPesticideConfirmation accommodationPesticideConfirmation = accommodationPesticideConfirmationRepository.findById(PesticideConfirmationId).orElseThrow(()->new ResourceNotFoundException("confirmation.not.found"));
        accommodationPesticideConfirmation.setConfirmation_by(dto.getConfirmationBy());
        accommodationPesticideConfirmation.setConfirmation_date(LocalDate.now());
        accommodationPesticideConfirmation.setConfirmation_type(dto.getConfirmationType());
        accommodationPesticideConfirmation.setDescription(dto.getDescription());
        accommodationPesticideConfirmation.setCreated_at(LocalDate.now());
        accommodationPesticideConfirmationRepository.save(accommodationPesticideConfirmation);
    }

    @Override
    public void deletePesticideConfirmation(Long PesticideConfirmationId) {
        accommodationPesticideConfirmationRepository.deleteById(PesticideConfirmationId);
    }
}
