package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.Accommodation;
import com.kpb.accomodationservice.domain.AccommodationArea;
import com.kpb.accomodationservice.dto.AccommodationCreateUpdateRequestDTO;
import com.kpb.accomodationservice.dto.AccommodationDetailResponseDTO;
import com.kpb.accomodationservice.dto.AccommodationListResponseDTO;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.AccommodationRepository;
import com.kpb.accomodationservice.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Override
    public List<AccommodationListResponseDTO> accommodationListResponseDTOS() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream().map(accommodation->{
            AccommodationListResponseDTO accommodationListResponseDTO = new AccommodationListResponseDTO();
            accommodationListResponseDTO.setAccommodationId(accommodation.getAccommodation_id());
            accommodationListResponseDTO.setAccommodationName(accommodation.getAccommodation_name());
            accommodationListResponseDTO.setAccommodationNumber(accommodation.getAccommodation_number());
            accommodationListResponseDTO.setAccommodationType(accommodation.getAccommodation_type());
            accommodationListResponseDTO.setUnit(accommodation.getUnit());
            accommodationListResponseDTO.setCreatedAt(accommodation.getCreated_at());
            return accommodationListResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public AccommodationDetailResponseDTO accommodationDetailResponseDTO(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(()->new ResourceNotFoundException("accommodation.not.found"));
        AccommodationDetailResponseDTO accommodationDetailResponseDTO = new AccommodationDetailResponseDTO();
        accommodationDetailResponseDTO.setAccommodationId(accommodation.getAccommodation_id());
        accommodationDetailResponseDTO.setAccommodationName(accommodation.getAccommodation_name());
        accommodationDetailResponseDTO.setAccommodationNumber(accommodation.getAccommodation_number());
        accommodationDetailResponseDTO.setAccommodationType(accommodation.getAccommodation_type());
        accommodationDetailResponseDTO.setUnit(accommodation.getUnit());
        accommodationDetailResponseDTO.setDescription(accommodation.getDescription());

        List<AccommodationArea> accommodationAreaList = accommodation.getAccommodationAreaList().stream().map((area) -> {
            AccommodationArea accommodationArea = new AccommodationArea();
            accommodationArea.setAccommodation_area_id(area.getAccommodation_area_id());
            accommodationArea.setProvince(area.getProvince());
            accommodationArea.setProvince_code(area.getProvince_code());
            accommodationArea.setCity(area.getCity());
            accommodationArea.setCity_code(area.getCity_code());
            accommodationArea.setDistrict(area.getDistrict());
            accommodationArea.setDistrict_code(area.getDistrict_code());
            accommodationArea.setSub_district(area.getSub_district());
            accommodationArea.setSub_district_code(area.getSub_district_code());
            return accommodationArea;
        }).toList();
        accommodationDetailResponseDTO.setAccommodationAreas(accommodationAreaList);
        return accommodationDetailResponseDTO;
    }

    @Override
    public void createAccommodation(AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO) {

        List<AccommodationArea> accommodationAreas = accommodationCreateUpdateRequestDTO.getAccommodationAreaList().stream().map(accommodationAreaDetail->{
            AccommodationArea accommodationArea = new AccommodationArea();
            accommodationArea.setCity(accommodationAreaDetail.getCity());
            accommodationArea.setCity_code(accommodationAreaDetail.getCity_code());
            accommodationArea.setDistrict(accommodationAreaDetail.getDistrict());
            accommodationArea.setDistrict_code(accommodationAreaDetail.getDistrict_code());
            accommodationArea.setProvince(accommodationAreaDetail.getProvince());
            accommodationArea.setProvince_code(accommodationAreaDetail.getProvince_code());
            accommodationArea.setSub_district(accommodationAreaDetail.getSub_district());
            accommodationArea.setSub_district_code(accommodationAreaDetail.getSub_district_code());
            return accommodationArea;
        }).collect(Collectors.toList());

        Accommodation accommodation = new Accommodation();
        accommodation.setAccommodation_name(accommodationCreateUpdateRequestDTO.getAccommodationName());
        accommodation.setAccommodation_number(accommodationCreateUpdateRequestDTO.getAccommodationNumber());
        accommodation.setAccommodation_type(accommodationCreateUpdateRequestDTO.getAccommodationType());
        accommodation.setDescription(accommodationCreateUpdateRequestDTO.getDescription());
        accommodation.setUnit(accommodationCreateUpdateRequestDTO.getUnit());
        accommodation.setAccommodationAreaList(accommodationAreas);
        accommodationRepository.save(accommodation);
    }

    @Override
    public void updateAccommodation(Long accommodationId,AccommodationCreateUpdateRequestDTO accommodationCreateUpdateRequestDTO) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow(()->new ResourceNotFoundException("accommodation.not.found"));
        accommodation.getAccommodationAreaList().clear();

        List<AccommodationArea> accommodationAreas = accommodationCreateUpdateRequestDTO.getAccommodationAreaList().stream().map(accommodationAreaDetail->{
            AccommodationArea accommodationArea = new AccommodationArea();
            accommodationArea.setCity(accommodationAreaDetail.getCity());
            accommodationArea.setCity_code(accommodationAreaDetail.getCity_code());
            accommodationArea.setDistrict(accommodationAreaDetail.getDistrict());
            accommodationArea.setDistrict_code(accommodationAreaDetail.getDistrict_code());
            accommodationArea.setProvince(accommodationAreaDetail.getProvince());
            accommodationArea.setProvince_code(accommodationAreaDetail.getProvince_code());
            accommodationArea.setSub_district(accommodationAreaDetail.getSub_district());
            accommodationArea.setSub_district_code(accommodationAreaDetail.getSub_district_code());
            return accommodationArea;
        }).collect(Collectors.toList());

        accommodation.setAccommodation_name(accommodationCreateUpdateRequestDTO.getAccommodationName());
        accommodation.setAccommodation_number(accommodationCreateUpdateRequestDTO.getAccommodationNumber());
        accommodation.setAccommodation_type(accommodationCreateUpdateRequestDTO.getAccommodationType());
        accommodation.setDescription(accommodationCreateUpdateRequestDTO.getDescription());
        accommodation.setUnit(accommodationCreateUpdateRequestDTO.getUnit());
        accommodation.setAccommodationAreaList(accommodationAreas);
        accommodationRepository.save(accommodation);
    }

    @Override
    public void deleteAccommodation(Long accommodationId) {
        accommodationRepository.deleteById(accommodationId);
    }
}
