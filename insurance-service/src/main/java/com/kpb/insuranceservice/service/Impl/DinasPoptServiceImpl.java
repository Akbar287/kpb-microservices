package com.kpb.insuranceservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.insuranceservice.domain.DinasPopt;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasPopt.DinasPoptListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.DinasPoptRepository;
import com.kpb.insuranceservice.service.DinasPoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DinasPoptServiceImpl implements DinasPoptService {

    @Autowired
    private DinasPoptRepository dinasPoptRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "dinasPoptId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<DinasPopt> dinasPopt = dinasPoptRepository.findAllByNamaKetuaLike(search, pageable);

        return this.paginationResponse(dinasPopt);
    }

    @Override
    public List<DinasPoptListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<DinasPopt> dinasPopt) {
        List<DinasPoptListResponseDTO> dinasPoptListResponseDTOS = dinasPopt.stream().map(popt-> {
            CreateUserResponse userResponse = userCreateClient.getUser(popt.getUserId());

            DinasPoptListResponseDTO dto = new DinasPoptListResponseDTO();
            dto.setDinasPoptId(popt.getDinasPoptId());
            dto.setNama(userResponse.getNama());
            dto.setAlamat(popt.getAlamat());
            dto.setNamaKetua(popt.getNamaKetua());
            dto.setRegister(popt.isRegister());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(dinasPoptListResponseDTOS);
        paginationResponse.setEmpty(dinasPopt.isEmpty());
        paginationResponse.setFirst(dinasPopt.isFirst());
        paginationResponse.setLast(dinasPopt.isLast());
        paginationResponse.setNumber(dinasPopt.getNumber());
        paginationResponse.setPageable(dinasPopt.getPageable());
        paginationResponse.setSize(dinasPopt.getSize());
        paginationResponse.setSort(dinasPopt.getSort());
        paginationResponse.setTotalElements(dinasPopt.getTotalElements());
        paginationResponse.setNumberOfElements(dinasPopt.getNumberOfElements());
        paginationResponse.setTotalPages(dinasPopt.getTotalPages());
        return paginationResponse;
    }

    @Override
    public DinasPoptDetailResponseDTO findDetail(Long dinasPoptId) {
        DinasPopt dinasPopt = dinasPoptRepository.findById(dinasPoptId).orElseThrow(()->new ResourceNotFoundException("dinas.popt.not.found"));

        CreateUserResponse userResponse = userCreateClient.getUser(dinasPopt.getUserId());

        DinasPoptDetailResponseDTO dto = new DinasPoptDetailResponseDTO();
        dto.setDinasPoptId(dinasPopt.getDinasPoptId());
        dto.setNama(userResponse.getNama());
        dto.setEmail(userResponse.getEmail());
        dto.setUsername(userResponse.getUsername());
        dto.setNomorTelepon(userResponse.getNomorTelepon());
        dto.setFileGambar(userResponse.getFileGambar());
        dto.setAlamat(dinasPopt.getAlamat());
        dto.setNamaKetua(dinasPopt.getNamaKetua());
        dto.setRegister(dinasPopt.isRegister());
        return dto;
    }

    @Override
    public void create(DinasPoptCreateUpdateRequestDTO dto) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setRole("POPT");
        userRequest.setFileGambar(dto.getFileGambar());
        CreateUserResponse userResponse = userCreateClient.createUser(userRequest);

        DinasPopt dinasPopt = new DinasPopt();
        dinasPopt.setUserId(userResponse.getUserId());
        dinasPopt.setAlamat(dinasPopt.getAlamat());
        dinasPopt.setNamaKetua(dto.getNamaKetua());
        dinasPopt.setRegister(dinasPopt.isRegister());
        dinasPoptRepository.save(dinasPopt);
    }

    @Override
    public void update(Long dinasPoptId, DinasPoptCreateUpdateRequestDTO dto) {
        DinasPopt dinasPopt = dinasPoptRepository.findById(dinasPoptId).orElseThrow(()->new ResourceNotFoundException("dinas.popt.not.found"));
        dinasPopt.setAlamat(dinasPopt.getAlamat());
        dinasPopt.setNamaKetua(dto.getNamaKetua());
        dinasPopt.setRegister(dinasPopt.isRegister());
        dinasPoptRepository.save(dinasPopt);

        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setFileGambar(dto.getFileGambar());
        CreateUserResponse userResponse = userCreateClient.updateUser(dinasPopt.getUserId(), userRequest);
    }

    @Override
    public void delete(Long dinasPoptId) {
        dinasPoptRepository.deleteById(dinasPoptId);
    }
}
