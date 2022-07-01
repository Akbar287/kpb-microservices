package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.Accommodation;
import com.kpb.accomodationservice.domain.AccommodationConfirmation;
import com.kpb.accomodationservice.domain.AccommodationRegistration;
import com.kpb.accomodationservice.domain.AccommodationStatus;
import com.kpb.accomodationservice.dto.AccommodationRegistrationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.dto.AccommodationRegistrationDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationRegistrationListResponse;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.AccommodationRegistrationRepository;
import com.kpb.accomodationservice.repository.AccommodationRepository;
import com.kpb.accomodationservice.repository.AccommodationStatusRepository;
import com.kpb.accomodationservice.service.AccommodationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationRegistrationServiceImpl implements AccommodationRegistrationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationRegistrationRepository accommodationRegistrationRepository;

    @Autowired
    private AccommodationStatusRepository accommodationStatusRepository;

    @Override
    public List<AccommodationRegistrationListResponse> accommodationRegistrationListResponse(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(()->new ResourceNotFoundException("accommodation.not.found"));
        return accommodation.getAccommodationRegistrationList().stream().map((registration)->{
            AccommodationRegistrationListResponse accommodationRegistrationListResponse = new AccommodationRegistrationListResponse();
            accommodationRegistrationListResponse.setAccommodationId(accommodation.getAccommodation_id());
            accommodationRegistrationListResponse.setAccommodationRegistrationId(registration.getAccommodation_registration_id());
            accommodationRegistrationListResponse.setCommodity(registration.getCommodity());
            accommodationRegistrationListResponse.setPlantingTime(registration.getPlanting_time());
            accommodationRegistrationListResponse.setYear(registration.getYear());
            accommodationRegistrationListResponse.setAccommodationName(accommodation.getAccommodation_name());
            accommodationRegistrationListResponse.setCreatedAt(registration.getCreated_at());

            AccommodationStatus accommodationStatus = registration.getAccommodationStatusList().get(registration.getAccommodationStatusList().size() - 1);
            accommodationRegistrationListResponse.setStatusLabel(accommodationStatus.getLabel());
            accommodationRegistrationListResponse.setStatusValue(accommodationStatus.getValue());
            return accommodationRegistrationListResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public AccommodationRegistrationDetailResponseDTO accommodationRegistrationDetailResponseDTO(Long accommodationRegistrationId) {
        AccommodationRegistration accommodationRegistration = accommodationRegistrationRepository.findById(accommodationRegistrationId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));

        AccommodationRegistrationDetailResponseDTO dto = new AccommodationRegistrationDetailResponseDTO();
        dto.setAccommodationRegistrationId(accommodationRegistration.getAccommodation_registration_id());
        dto.setCommodity(accommodationRegistration.getCommodity());
        dto.setDescription(accommodationRegistration.getDescription());
        dto.setFileName(accommodationRegistration.getFile_name());
        dto.setFileProposal(accommodationRegistration.getFile_proposal());
        dto.setYear(accommodationRegistration.getYear());
        dto.setPlantingTime(accommodationRegistration.getPlanting_time());
        dto.setCreatedAt(accommodationRegistration.getCreated_at());

        AccommodationConfirmation accommodationConfirmation = new AccommodationConfirmation();
        accommodationConfirmation.setAccommodation_confirmation_id(accommodationRegistration.getAccommodationConfirmation().getAccommodation_confirmation_id());
        accommodationConfirmation.setConfirmation_by(accommodationRegistration.getAccommodationConfirmation().getConfirmation_by());
        accommodationConfirmation.setConfirmation_date(accommodationRegistration.getAccommodationConfirmation().getConfirmation_date());
        accommodationConfirmation.setConfirmation_type(accommodationRegistration.getAccommodationConfirmation().getConfirmation_type());
        accommodationConfirmation.setDescription(accommodationRegistration.getAccommodationConfirmation().getDescription());
        accommodationConfirmation.setCreated_at(accommodationConfirmation.getAccommodationRegistration().getCreated_at());
        dto.setAccommodationConfirmation(accommodationConfirmation);

        List<AccommodationStatus> accommodationStatusList = accommodationRegistration.getAccommodationStatusList().stream().map((status)->{
            AccommodationStatus accommodationStatus = new AccommodationStatus();
            accommodationStatus.setAccommodation_status_id(status.getAccommodation_status_id());
            accommodationStatus.setLabel(status.getLabel());
            accommodationStatus.setValue(status.getValue());
            accommodationStatus.setCreated_at(status.getCreated_at());
            return accommodationStatus;
        }).toList();
        dto.setAccommodationStatus(accommodationStatusList);

        return dto;
    }

    @Override
    public void createAccommodationRegistration(AccommodationRegistrationCreateUpdateRequestDTO accommodationRegistrationCreateUpdateRequestDTO) {

        Accommodation accommodation = accommodationRepository.findById(accommodationRegistrationCreateUpdateRequestDTO.getAccommodationId()).orElseThrow(()->new ResourceNotFoundException("accommodation.not.found"));

        List<AccommodationStatus> accommodationStatusList = new ArrayList<>();
        AccommodationStatus accommodationStatus = new AccommodationStatus();
        accommodationStatus.setLabel("Pengajuan Bantuan");
        accommodationStatus.setValue("Pengajuan Berhasil didaftarkan");
        accommodationStatus.setCreated_at(LocalDate.now());
        accommodationStatusRepository.save(accommodationStatus);
        accommodationStatusList.add(accommodationStatus);

        AccommodationRegistration accommodationRegistration = new AccommodationRegistration();
        accommodationRegistration.setCommodity(accommodationRegistrationCreateUpdateRequestDTO.getCommodity());
        accommodationRegistration.setFile_name(accommodationRegistrationCreateUpdateRequestDTO.getFileName());
        accommodationRegistration.setFile_proposal(accommodationRegistrationCreateUpdateRequestDTO.getFileProposal());
        accommodationRegistration.setPlanting_time(accommodationRegistrationCreateUpdateRequestDTO.getPlantingTime());
        accommodationRegistration.setYear(accommodationRegistrationCreateUpdateRequestDTO.getYear());
        accommodationRegistration.setFarmer_id(accommodationRegistrationCreateUpdateRequestDTO.getFarmerId());
        accommodationRegistration.setDescription(accommodationRegistrationCreateUpdateRequestDTO.getDescription());
        accommodationRegistration.setAccommodationStatusList(accommodationStatusList);
        accommodationRegistration.setAccommodation(accommodation);
        accommodationRegistrationRepository.save(accommodationRegistration);
    }

    @Override
    public void updateAccommodationRegistration(Long accommodationRegistrationId, AccommodationRegistrationCreateUpdateRequestDTO accommodationRegistrationCreateUpdateRequestDTO) {
        AccommodationRegistration accommodationRegistration = accommodationRegistrationRepository.findById(accommodationRegistrationId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));
        accommodationRegistration.setCommodity(accommodationRegistrationCreateUpdateRequestDTO.getCommodity());
        accommodationRegistration.setFile_name(accommodationRegistrationCreateUpdateRequestDTO.getFileName());
        accommodationRegistration.setFile_proposal(accommodationRegistrationCreateUpdateRequestDTO.getFileProposal());
        accommodationRegistration.setPlanting_time(accommodationRegistrationCreateUpdateRequestDTO.getPlantingTime());
        accommodationRegistration.setYear(accommodationRegistrationCreateUpdateRequestDTO.getYear());
        accommodationRegistration.setDescription(accommodationRegistrationCreateUpdateRequestDTO.getDescription());
        accommodationRegistrationRepository.save(accommodationRegistration);
    }

    @Override
    public void deleteAccommodationRegistration(Long accommodationRegistrationId) {
        accommodationRegistrationRepository.deleteById(accommodationRegistrationId);
    }
}
