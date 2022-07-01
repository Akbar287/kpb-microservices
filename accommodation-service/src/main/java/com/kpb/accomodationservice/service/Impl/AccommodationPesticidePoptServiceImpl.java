package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.AccommodationPesticideRegistration;
import com.kpb.accomodationservice.domain.PoptRecommendation;
import com.kpb.accomodationservice.dto.AccommodationPoptRecommendationDTO;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.AccommodationPesticideRegistrationRepository;
import com.kpb.accomodationservice.repository.PoptRecommendationRepository;
import com.kpb.accomodationservice.service.AccommodationPesticidePoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationPesticidePoptServiceImpl implements AccommodationPesticidePoptService {

    @Autowired
    private AccommodationPesticideRegistrationRepository accommodationPesticideRegistrationRepository;

    @Autowired
    private PoptRecommendationRepository poptRecommendationRepository;

    @Override
    public void createPoptRecommendation(Long accommodationPesticideRegistrationId, AccommodationPoptRecommendationDTO dto) {
        AccommodationPesticideRegistration accommodationPesticideRegistration = accommodationPesticideRegistrationRepository.findById(accommodationPesticideRegistrationId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));

        PoptRecommendation poptRecommendation = new PoptRecommendation();
        poptRecommendation.setDescription(dto.getDescription());
        poptRecommendation.setFile_name(dto.getFileName());
        poptRecommendation.setFile_document(dto.getFileDocument());
        poptRecommendation.setPopt_officer_id(dto.getPoptOfficer());
        poptRecommendation.setStatement_letter_number(dto.getStatementLetterNumber());
        poptRecommendation.setStatement_letter_date(dto.getStatementLetterDate());
        poptRecommendation.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
        poptRecommendationRepository.save(poptRecommendation);

    }

    @Override
    public void updatePoptRecommendation(Long PoptRecommendationId, AccommodationPoptRecommendationDTO dto) {
        PoptRecommendation poptRecommendation = poptRecommendationRepository.findById(PoptRecommendationId).orElseThrow(()->new ResourceNotFoundException("recommendation.not.found"));
        poptRecommendation.setDescription(dto.getDescription());
        poptRecommendation.setFile_name(dto.getFileName());
        poptRecommendation.setFile_document(dto.getFileDocument());
        poptRecommendation.setPopt_officer_id(dto.getPoptOfficer());
        poptRecommendation.setStatement_letter_number(dto.getStatementLetterNumber());
        poptRecommendation.setStatement_letter_date(dto.getStatementLetterDate());
        poptRecommendationRepository.save(poptRecommendation);
    }

    @Override
    public void deletePoptRecommendation(Long PoptRecommendationId) {
        poptRecommendationRepository.deleteById(PoptRecommendationId);
    }
}
