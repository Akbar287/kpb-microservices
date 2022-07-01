package com.kpb.insuranceservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.insuranceservice.domain.DinasKabupaten;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenDetailResponseDTO;
import com.kpb.insuranceservice.dto.DinasKabupaten.DinasKabupatenListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.DinasKabupatenRepository;
import com.kpb.insuranceservice.service.DinasKabupatenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DinasKabupatenServiceImpl implements DinasKabupatenService {

    @Autowired
    private DinasKabupatenRepository dinasKabupatenRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "distributorId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<DinasKabupaten> asuransi = dinasKabupatenRepository.findAllByNamaKetuaLike(search, pageable);

        return this.paginationResponse(asuransi);
    }

    @Override
    public List<DinasKabupatenListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<DinasKabupaten> dinasKabupaten) {
        List<DinasKabupatenListResponseDTO> dinasKabupatenListResponseDTOS = dinasKabupaten.stream().map(kab-> {

            CreateUserResponse userResponse = userCreateClient.getUser(kab.getUserId());

            DinasKabupatenListResponseDTO dto = new DinasKabupatenListResponseDTO();
            dto.setDinasKabupatenId(kab.getDinasKabupatenId());
            dto.setNama(userResponse.getNama());
            dto.setEmail(userResponse.getEmail());
            dto.setNamaKabupaten(dto.getNamaKabupaten());
            dto.setKota(kab.isKota());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(dinasKabupatenListResponseDTOS);
        paginationResponse.setEmpty(dinasKabupaten.isEmpty());
        paginationResponse.setFirst(dinasKabupaten.isFirst());
        paginationResponse.setLast(dinasKabupaten.isLast());
        paginationResponse.setNumber(dinasKabupaten.getNumber());
        paginationResponse.setPageable(dinasKabupaten.getPageable());
        paginationResponse.setSize(dinasKabupaten.getSize());
        paginationResponse.setSort(dinasKabupaten.getSort());
        paginationResponse.setTotalElements(dinasKabupaten.getTotalElements());
        paginationResponse.setNumberOfElements(dinasKabupaten.getNumberOfElements());
        paginationResponse.setTotalPages(dinasKabupaten.getTotalPages());
        return paginationResponse;
    }

    @Override
    public DinasKabupatenDetailResponseDTO findDetail(Long dinasKabupatenId) {
        DinasKabupaten dinasKabupaten = dinasKabupatenRepository.findById(dinasKabupatenId).orElseThrow(()->new ResourceNotFoundException("dinas.kabupaten.not.found"));

        CreateUserResponse userResponse =userCreateClient.getUser(dinasKabupatenId);

        DinasKabupatenDetailResponseDTO dto = new DinasKabupatenDetailResponseDTO();
        dto.setDinasKabupatenId(dinasKabupaten.getDinasKabupatenId());
        dto.setNama(userResponse.getNama());
        dto.setEmail(userResponse.getEmail());
        dto.setUsername(userResponse.getUsername());
        dto.setNomorTelepon(userResponse.getNomorTelepon());
        dto.setFileGambar(userResponse.getFileGambar());
        dto.setAlamat(dinasKabupaten.getAlamat());
        dto.setNamaKetua(dinasKabupaten.getNamaKetua());
        dto.setNamaKabupaten(dinasKabupaten.getNamaKabupaten());
        dto.setKodeKabupaten(dinasKabupaten.getKodeKabupaten());
        dto.setRegister(dinasKabupaten.isRegister());
        dto.setKota(dinasKabupaten.isKota());
        return dto;
    }

    @Override
    public void create(DinasKabupatenCreateUpdateRequestDTO dto) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setRole("KABKOTA");
        userRequest.setFileGambar(dto.getFileGambar());
        CreateUserResponse userResponse = userCreateClient.createUser(userRequest);

        DinasKabupaten dinasKabupaten = new DinasKabupaten();
        dinasKabupaten.setNamaKabupaten(dto.getNamaKabupaten());
        dinasKabupaten.setKodeKabupaten(dto.getKodeKabupaten());
        dinasKabupaten.setAlamat(dto.getAlamat());
        dinasKabupaten.setRegister(dto.isRegister());
        dinasKabupaten.setKota(dto.isKota());
        dinasKabupaten.setNamaKetua(dto.getNamaKetua());
        dinasKabupaten.setUserId(userResponse.getUserId());
        dinasKabupatenRepository.save(dinasKabupaten);
    }

    @Override
    public void update(Long dinasKabupatenId, DinasKabupatenCreateUpdateRequestDTO dto) {
        DinasKabupaten dinasKabupaten = dinasKabupatenRepository.findById(dinasKabupatenId).orElseThrow(()->new ResourceNotFoundException("dinas.kab.not.found"));
        dinasKabupaten.setNamaKabupaten(dto.getNamaKabupaten());
        dinasKabupaten.setKodeKabupaten(dto.getKodeKabupaten());
        dinasKabupaten.setAlamat(dto.getAlamat());
        dinasKabupaten.setRegister(dto.isRegister());
        dinasKabupaten.setKota(dto.isKota());
        dinasKabupaten.setNamaKetua(dto.getNamaKetua());
        dinasKabupatenRepository.save(dinasKabupaten);

        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setNama(dto.getNama());
        userRequest.setEmail(dto.getEmail());
        userRequest.setUsername(dto.getUsername());
        userRequest.setPassword(dto.getPassword());
        userRequest.setNomorTelepon(dto.getNomorTelepon());
        userRequest.setFileGambar(dto.getFileGambar());
        CreateUserResponse userResponse = userCreateClient.updateUser(dinasKabupaten.getUserId(), userRequest);
    }

    @Override
    public void delete(Long dinasKabupatenId) {
        dinasKabupatenRepository.deleteById(dinasKabupatenId);
    }
}
