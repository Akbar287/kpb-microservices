package com.kpb.accomodationservice.service.Impl;

import com.kpb.accomodationservice.domain.*;
import com.kpb.accomodationservice.dto.*;
import com.kpb.accomodationservice.exception.ResourceNotFoundException;
import com.kpb.accomodationservice.repository.*;
import com.kpb.accomodationservice.service.AccommodationPesticideRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationPesticideRegistrationServiceImpl implements AccommodationPesticideRegistrationService {

    @Autowired
    private AccommodationPesticideRegistrationRepository accommodationPesticideRegistrationRepository;

    @Autowired
    private AccommodationPesticideFileRegistrationRepository accommodationPesticideFileRegistrationRepository;

    @Autowired
    private AccommodationPesticideAreaRepository accommodationPesticideAreaRepository;

    @Autowired
    private AccommodationPesticideStatusRepository accommodationPesticideStatusRepository;

    @Override
    public List<AccommodationPesticideListResponseDTO> findAllPesticideRegistration() {
        List<AccommodationPesticideRegistration> accommodationPesticideRegistration = accommodationPesticideRegistrationRepository.findAll();
        return accommodationPesticideRegistration.stream().map(registration->{
            AccommodationPesticideListResponseDTO dto = new AccommodationPesticideListResponseDTO();
            dto.setAccommodationPesticideId(registration.getAccommodation_pesticide_registration_id());
            dto.setBugType(registration.getBug_type());
            dto.setCommodity(registration.getCommodity());
            dto.setPesticideType(registration.getPesticide_type());
            dto.setLocation(registration.getLocation());
            dto.setYear(registration.getYear());
            dto.setPlantingTime(registration.getPlanting_time());
            dto.setFileUploaded(registration.getAccommodationPesticideFileRegistrationList().size());
            dto.setRecommendation(registration.getPoptRecommendations().getPopt_recommendation_id() != 0);
            dto.setConfirmation(registration.getAccommodationPesticideConfirmation().getAccommodation_pesticide_confirmation_id() != 0);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public AccommodationPesticideDetailResponseDTO findDetailPesticideRegistration(Long accommodationPesticideId) {
        AccommodationPesticideRegistration accommodationPesticideRegistration = accommodationPesticideRegistrationRepository.findById(accommodationPesticideId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));

        List<AccommodationPesticideFileRegistration> accommodationPesticideFileRegistrations = accommodationPesticideRegistration.getAccommodationPesticideFileRegistrationList().stream().map(file->{
            AccommodationPesticideFileRegistration fileDTO = new AccommodationPesticideFileRegistration();
            fileDTO.setAccommodation_pesticide_file_registration_id(file.getAccommodation_pesticide_file_registration_id());
            fileDTO.setFile_document(file.getFile_document());
            fileDTO.setFile_name(file.getFile_name());
            fileDTO.setDescription(file.getDescription());
            return fileDTO;
        }).toList();

        List<AccommodationPesticideStatus> accommodationPesticideStatusList = accommodationPesticideRegistration.getAccommodationPesticideStatusList().stream().map(status->{
            AccommodationPesticideStatus accommodationPesticideStatus = new AccommodationPesticideStatus();
            accommodationPesticideStatus.setAccommodation_pesticide_status_id(status.getAccommodation_pesticide_status_id());
            accommodationPesticideStatus.setLabel(status.getLabel());
            accommodationPesticideStatus.setValue(status.getValue());
            accommodationPesticideStatus.setCreated_at(status.getCreated_at());
            return accommodationPesticideStatus;
        }).toList();

        List<AccommodationPesticideArea> accommodationPesticideAreas = accommodationPesticideRegistration.getAccommodationPesticideAreaList().stream().map(area->{
            AccommodationPesticideArea accommodationPesticideArea = new AccommodationPesticideArea();
            accommodationPesticideArea.setAccommodation_pesticide_area_id(area.getAccommodation_pesticide_area_id());
            accommodationPesticideArea.setProvince(area.getProvince());
            accommodationPesticideArea.setProvince_code(area.getProvince_code());
            accommodationPesticideArea.setCity(area.getCity());
            accommodationPesticideArea.setCity_code(area.getCity_code());
            accommodationPesticideArea.setDistrict(area.getDistrict());
            accommodationPesticideArea.setDistrict_code(area.getDistrict_code());
            accommodationPesticideArea.setSub_district(area.getSub_district());
            accommodationPesticideArea.setSub_district_code(area.getSub_district_code());
            return accommodationPesticideArea;
        }).toList();

        PoptRecommendation poptRecommendation = new PoptRecommendation();
        poptRecommendation.setPopt_officer_id(accommodationPesticideRegistration.getPoptRecommendations().getPopt_officer_id());
        poptRecommendation.setDescription(accommodationPesticideRegistration.getPoptRecommendations().getDescription());
        poptRecommendation.setFile_name(accommodationPesticideRegistration.getPoptRecommendations().getFile_name());
        poptRecommendation.setFile_document(accommodationPesticideRegistration.getPoptRecommendations().getFile_document());
        poptRecommendation.setStatement_letter_date(accommodationPesticideRegistration.getPoptRecommendations().getStatement_letter_date());
        poptRecommendation.setStatement_letter_number(accommodationPesticideRegistration.getPoptRecommendations().getStatement_letter_number());

        AccommodationPesticideConfirmation accommodationPesticideConfirmation = new AccommodationPesticideConfirmation();
        accommodationPesticideConfirmation.setAccommodation_pesticide_confirmation_id(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getAccommodation_pesticide_confirmation_id());
        accommodationPesticideConfirmation.setConfirmation_by(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getConfirmation_by());
        accommodationPesticideConfirmation.setConfirmation_date(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getConfirmation_date());
        accommodationPesticideConfirmation.setConfirmation_type(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getConfirmation_type());
        accommodationPesticideConfirmation.setDescription(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getDescription());
        accommodationPesticideConfirmation.setCreated_at(accommodationPesticideRegistration.getAccommodationPesticideConfirmation().getCreated_at());

        AccommodationPesticideDetailResponseDTO dto = new AccommodationPesticideDetailResponseDTO();
        dto.setAccommodationPesticideId(accommodationPesticideRegistration.getAccommodation_pesticide_registration_id());
        dto.setArea(accommodationPesticideRegistration.getArea());
        dto.setBugType(accommodationPesticideRegistration.getBug_type());
        dto.setCommodity(accommodationPesticideRegistration.getCommodity());
        dto.setPesticideType(accommodationPesticideRegistration.getPesticide_type());
        dto.setLocation(accommodationPesticideRegistration.getLocation());
        dto.setDescription(accommodationPesticideRegistration.getDescription());
        dto.setYear(accommodationPesticideRegistration.getYear());
        dto.setPlantingTime(accommodationPesticideRegistration.getPlanting_time());
        dto.setFarmerId(accommodationPesticideRegistration.getFarmer_id());
        dto.setPoptRecommendation(poptRecommendation);
        dto.setAccommodationPesticideConfirmation(accommodationPesticideConfirmation);
        dto.setAccommodationPesticideAreas(accommodationPesticideAreas);
        dto.setAccommodationPesticideStatus(accommodationPesticideStatusList);
        dto.setAccommodationPesticideFileRegistrations(accommodationPesticideFileRegistrations);
        return dto;
    }

    @Override
    public void createPesticideRegistration(AccommodationPesticideRegistrationCreateUpdateDTO dto) {
        AccommodationPesticideRegistration accommodationPesticideRegistration = new AccommodationPesticideRegistration();
        accommodationPesticideRegistration.setArea(dto.getArea());
        accommodationPesticideRegistration.setBug_type(dto.getBugType());
        accommodationPesticideRegistration.setCommodity(dto.getCommodity());
        accommodationPesticideRegistration.setDescription(dto.getDescription());
        accommodationPesticideRegistration.setFarmer_id(dto.getFarmerId());
        accommodationPesticideRegistration.setLocation(dto.getLocation());
        accommodationPesticideRegistration.setPesticide_type(dto.getPesticideType());
        accommodationPesticideRegistration.setPlanting_time(dto.getPlantingTime());
        accommodationPesticideRegistration.setYear(dto.getYear());
        accommodationPesticideRegistrationRepository.save(accommodationPesticideRegistration);

        List<AccommodationPesticideFileDTO> accommodationPesticideFileDTO = dto.getAccommodationPesticideFileDTOList();
        List<AccommodationPesticideFileRegistration> accommodationPesticideFileRegistrations = accommodationPesticideFileDTO.stream().map(file->{
            AccommodationPesticideFileRegistration accommodationPesticideFileRegistration = new AccommodationPesticideFileRegistration();
            accommodationPesticideFileRegistration.setFile_name(file.getFileName());
            accommodationPesticideFileRegistration.setFile_document(file.getFileDocument());
            accommodationPesticideFileRegistration.setDescription(file.getDescription());
            accommodationPesticideFileRegistration.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
            return accommodationPesticideFileRegistration;
        }).toList();
        accommodationPesticideFileRegistrationRepository.saveAll(accommodationPesticideFileRegistrations);

        List<AccommodationPesticideAreaDTO> accommodationPesticideAreaDTO = dto.getAccommodationPesticideAreaDTOS();
        List<AccommodationPesticideArea> accommodationPesticideAreas = accommodationPesticideAreaDTO.stream().map(area->{
            AccommodationPesticideArea accommodationPesticideArea = new AccommodationPesticideArea();
            accommodationPesticideArea.setProvince(area.getProvince());
            accommodationPesticideArea.setProvince_code(area.getProvinceCode());
            accommodationPesticideArea.setCity(area.getCity());
            accommodationPesticideArea.setCity_code(area.getCityCode());
            accommodationPesticideArea.setDistrict(area.getDistrict());
            accommodationPesticideArea.setDistrict_code(area.getDistrictCode());
            accommodationPesticideArea.setSub_district(area.getSubDistrict());
            accommodationPesticideArea.setSub_district_code(area.getSubDistrictCode());
            accommodationPesticideArea.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
            return accommodationPesticideArea;
        }).toList();
        accommodationPesticideAreaRepository.saveAll(accommodationPesticideAreas);

        AccommodationPesticideStatus accommodationPesticideStatus = new AccommodationPesticideStatus();
        accommodationPesticideStatus.setLabel("Pengajuan");
        accommodationPesticideStatus.setValue("Pengajuan Pestisida berhasil dilakukan");
        accommodationPesticideStatus.setCreated_at(LocalDate.now());
        accommodationPesticideStatus.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
        accommodationPesticideStatusRepository.save(accommodationPesticideStatus);
    }

    @Override
    public void updatePesticideRegistration(Long accommodationPesticideId, AccommodationPesticideRegistrationCreateUpdateDTO dto) {
        AccommodationPesticideRegistration accommodationPesticideRegistration = accommodationPesticideRegistrationRepository.findById(accommodationPesticideId).orElseThrow(()->new ResourceNotFoundException("registration.not.found"));
        accommodationPesticideRegistration.setArea(dto.getArea());
        accommodationPesticideRegistration.setBug_type(dto.getBugType());
        accommodationPesticideRegistration.setCommodity(dto.getCommodity());
        accommodationPesticideRegistration.setDescription(dto.getDescription());
        accommodationPesticideRegistration.setFarmer_id(dto.getFarmerId());
        accommodationPesticideRegistration.setLocation(dto.getLocation());
        accommodationPesticideRegistration.setPesticide_type(dto.getPesticideType());
        accommodationPesticideRegistration.setPlanting_time(dto.getPlantingTime());
        accommodationPesticideRegistration.setYear(dto.getYear());
        accommodationPesticideRegistrationRepository.save(accommodationPesticideRegistration);

        List<AccommodationPesticideFileDTO> accommodationPesticideFileDTO = dto.getAccommodationPesticideFileDTOList();
        accommodationPesticideFileDTO.clear();
        List<AccommodationPesticideFileRegistration> accommodationPesticideFileRegistrations = accommodationPesticideFileDTO.stream().map(file->{
            AccommodationPesticideFileRegistration accommodationPesticideFileRegistration = new AccommodationPesticideFileRegistration();
            accommodationPesticideFileRegistration.setFile_name(file.getFileName());
            accommodationPesticideFileRegistration.setFile_document(file.getFileDocument());
            accommodationPesticideFileRegistration.setDescription(file.getDescription());
            accommodationPesticideFileRegistration.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
            return accommodationPesticideFileRegistration;
        }).toList();
        accommodationPesticideFileRegistrationRepository.saveAll(accommodationPesticideFileRegistrations);

        List<AccommodationPesticideAreaDTO> accommodationPesticideAreaDTO = dto.getAccommodationPesticideAreaDTOS();
        accommodationPesticideAreaDTO.clear();
        List<AccommodationPesticideArea> accommodationPesticideAreas = accommodationPesticideAreaDTO.stream().map(area->{
            AccommodationPesticideArea accommodationPesticideArea = new AccommodationPesticideArea();
            accommodationPesticideArea.setProvince(area.getProvince());
            accommodationPesticideArea.setProvince_code(area.getProvinceCode());
            accommodationPesticideArea.setCity(area.getCity());
            accommodationPesticideArea.setCity_code(area.getCityCode());
            accommodationPesticideArea.setDistrict(area.getDistrict());
            accommodationPesticideArea.setDistrict_code(area.getDistrictCode());
            accommodationPesticideArea.setSub_district(area.getSubDistrict());
            accommodationPesticideArea.setSub_district_code(area.getSubDistrictCode());
            accommodationPesticideArea.setAccommodationPesticideRegistration(accommodationPesticideRegistration);
            return accommodationPesticideArea;
        }).toList();
        accommodationPesticideAreaRepository.saveAll(accommodationPesticideAreas);
    }

    @Override
    public void deletePesticideRegistration(Long accommodationPesticideId) {
        accommodationPesticideRegistrationRepository.deleteById(accommodationPesticideId);
    }
}
