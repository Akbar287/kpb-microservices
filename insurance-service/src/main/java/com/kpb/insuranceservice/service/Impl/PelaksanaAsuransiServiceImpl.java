package com.kpb.insuranceservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.insuranceservice.domain.PelaksanaAsuransi;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PelaksanaAsuransi.PelaksanaAsuransiListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.PelaksanaAsuransiRepository;
import com.kpb.insuranceservice.service.PelaksanaAsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PelaksanaAsuransiServiceImpl implements PelaksanaAsuransiService {

    @Autowired
    private PelaksanaAsuransiRepository pelaksanaAsuransiRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "pelaksanaAsuransiId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<PelaksanaAsuransi> pelaksanaAsuransi = pelaksanaAsuransiRepository.findAllByNamaKetuaLike(search, pageable);

        return this.paginationResponse(pelaksanaAsuransi);
    }

    @Override
    public List<PelaksanaAsuransiListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse (Page<PelaksanaAsuransi> pelaksanaAsuransi) {
        List<PelaksanaAsuransiListResponseDTO> pelaksanaAsuransiListResponseDTOS = pelaksanaAsuransi.stream().map(pa->{
            CreateUserResponse userResponse = userCreateClient.getUser(pa.getUserId());

            PelaksanaAsuransiListResponseDTO pelaksanaAsuransiListResponseDTO = new PelaksanaAsuransiListResponseDTO();
            pelaksanaAsuransiListResponseDTO.setPelaksanaAsuransiId(pa.getPelaksanaAsuransiId());
            pelaksanaAsuransiListResponseDTO.setNama(userResponse.getNama());
            pelaksanaAsuransiListResponseDTO.setNamaKetua(userResponse.getNama());
            pelaksanaAsuransiListResponseDTO.setEmail(userResponse.getEmail());
            pelaksanaAsuransiListResponseDTO.setAlamat(pa.getAlamat());
            pelaksanaAsuransiListResponseDTO.setJenisAsuransi(pa.getJenisAsuransi());
            return pelaksanaAsuransiListResponseDTO;
        }).toList();


        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(pelaksanaAsuransiListResponseDTOS);
        paginationResponse.setEmpty(pelaksanaAsuransi.isEmpty());
        paginationResponse.setFirst(pelaksanaAsuransi.isFirst());
        paginationResponse.setLast(pelaksanaAsuransi.isLast());
        paginationResponse.setNumber(pelaksanaAsuransi.getNumber());
        paginationResponse.setPageable(pelaksanaAsuransi.getPageable());
        paginationResponse.setSize(pelaksanaAsuransi.getSize());
        paginationResponse.setSort(pelaksanaAsuransi.getSort());
        paginationResponse.setTotalElements(pelaksanaAsuransi.getTotalElements());
        paginationResponse.setNumberOfElements(pelaksanaAsuransi.getNumberOfElements());
        paginationResponse.setTotalPages(pelaksanaAsuransi.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PelaksanaAsuransiDetailResponseDTO findDetail(Long pelaksanaAsuransiId) {
        PelaksanaAsuransi pelaksanaAsuransi = pelaksanaAsuransiRepository.findById(pelaksanaAsuransiId).orElseThrow(()->new ResourceNotFoundException("pelaksana.asuransi.not.found"));

        CreateUserResponse userResponse = userCreateClient.getUser(pelaksanaAsuransi.getUserId());

        PelaksanaAsuransiDetailResponseDTO dto = new PelaksanaAsuransiDetailResponseDTO();
        dto.setPelaksanaAsuransiId(pelaksanaAsuransi.getPelaksanaAsuransiId());
        dto.setNamaKetua(pelaksanaAsuransi.getNamaKetua());
        dto.setNama(userResponse.getNama());
        dto.setEmail(userResponse.getEmail());
        dto.setUsername(userResponse.getUsername());
        dto.setNomorTelepon(userResponse.getNomorTelepon());
        dto.setFileGambar(userResponse.getFileGambar());
        dto.setAlamat(pelaksanaAsuransi.getAlamat());
        dto.setJenisAsuransi(pelaksanaAsuransi.getJenisAsuransi());
        dto.setKeterangan(pelaksanaAsuransi.getKeterangan());
        return dto;
    }

    @Override
    public void create(PelaksanaAsuransiCreateUpdateRequestDTO dto) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setFileGambar(dto.getFileGambar());
        userRequest.setRole("ASURANSI");
        CreateUserResponse userResponse = userCreateClient.createUser(userRequest);

        PelaksanaAsuransi pelaksanaAsuransi = new PelaksanaAsuransi();
        pelaksanaAsuransi.setNamaKetua(dto.getNamaKetua());
        pelaksanaAsuransi.setJenisAsuransi(dto.getJenisAsuransi());
        pelaksanaAsuransi.setAlamat(dto.getAlamat());
        pelaksanaAsuransi.setUserId(userResponse.getUserId());
        pelaksanaAsuransi.setKeterangan(dto.getKeterangan());
        pelaksanaAsuransi.setCreatedAt(LocalDate.now());
        pelaksanaAsuransi.setUpdatedAt(LocalDate.now());
        pelaksanaAsuransiRepository.save(pelaksanaAsuransi);
    }

    @Override
    public void update(Long pelaksanaAsuransiId, PelaksanaAsuransiCreateUpdateRequestDTO dto) {
        PelaksanaAsuransi pelaksanaAsuransi = pelaksanaAsuransiRepository.findById(pelaksanaAsuransiId).orElseThrow(()->new ResourceNotFoundException("pelaksana.asuransi.not.found"));
        pelaksanaAsuransi.setNamaKetua(dto.getNamaKetua());
        pelaksanaAsuransi.setJenisAsuransi(dto.getJenisAsuransi());
        pelaksanaAsuransi.setAlamat(dto.getAlamat());
        pelaksanaAsuransi.setKeterangan(dto.getKeterangan());
        pelaksanaAsuransi.setUpdatedAt(LocalDate.now());
        pelaksanaAsuransiRepository.save(pelaksanaAsuransi);

        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setFileGambar(dto.getFileGambar());
        CreateUserResponse userResponse = userCreateClient.updateUser(pelaksanaAsuransi.getUserId(), userRequest);
    }

    @Override
    public void delete(Long pelaksanaAsuransiId) {
        pelaksanaAsuransiRepository.deleteById(pelaksanaAsuransiId);
    }
}
