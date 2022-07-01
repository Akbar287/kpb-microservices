package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.memberservice.domain.Kios;
import com.kpb.memberservice.domain.Penyuluh;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.penyuluh.PenyuluhCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.penyuluh.PenyuluhListResponseDTO;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.KiosRepository;
import com.kpb.memberservice.repository.PenyuluhRepository;
import com.kpb.memberservice.service.PenyuluhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class PenyuluhServiceImpl implements PenyuluhService {

    @Autowired
    private PenyuluhRepository penyuluhRepository;

    @Autowired
    private KiosRepository kiosRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "penyuluhId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Penyuluh> penyuluh = penyuluhRepository.findAllByAlamatLike(search, pageable);

        List<PenyuluhListResponseDTO> penyuluhListResponseDTOS = penyuluh.stream().map(b -> {
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            PenyuluhListResponseDTO dto =new PenyuluhListResponseDTO();
            dto.setPenyuluhId(b.getPenyuluhId());
            dto.setNama(createUserResponse.getNama());
            dto.setEmail(createUserResponse.getEmail());
            dto.setFileGambar(createUserResponse.getFileGambar());
            dto.setNomorTelepon(createUserResponse.getNomorTelepon());
            dto.setAlamat(b.getAlamat());
            dto.setUsername(createUserResponse.getUsername());
            dto.setVerifikasi(b.isVerifikasi());
            dto.setCreatedAt(b.getCreatedAt());
            dto.setUpdatedAt(b.getUpdatedAt());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(penyuluhListResponseDTOS);
        paginationResponse.setEmpty(penyuluh.isEmpty());
        paginationResponse.setFirst(penyuluh.isFirst());
        paginationResponse.setLast(penyuluh.isLast());
        paginationResponse.setNumber(penyuluh.getNumber());
        paginationResponse.setPageable(penyuluh.getPageable());
        paginationResponse.setSize(penyuluh.getSize());
        paginationResponse.setSort(penyuluh.getSort());
        paginationResponse.setTotalElements(penyuluh.getTotalElements());
        paginationResponse.setNumberOfElements(penyuluh.getNumberOfElements());
        paginationResponse.setTotalPages(penyuluh.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PenyuluhListResponseDTO findDetail(Long penyuluhId) {
        Penyuluh penyuluh = penyuluhRepository.findById(penyuluhId).orElseThrow(()->new ResourceNotFoundException("penyuluh.not.found"));

        CreateUserResponse createUserResponse = userCreateClient.getUser(penyuluh.getUserId());
        PenyuluhListResponseDTO dto =new PenyuluhListResponseDTO();
        dto.setPenyuluhId(penyuluh.getPenyuluhId());
        dto.setNama(createUserResponse.getNama());
        dto.setEmail(createUserResponse.getEmail());
        dto.setFileGambar(createUserResponse.getFileGambar());
        dto.setNomorTelepon(createUserResponse.getNomorTelepon());
        dto.setAlamat(penyuluh.getAlamat());
        dto.setUsername(createUserResponse.getUsername());
        dto.setVerifikasi(penyuluh.isVerifikasi());
        dto.setCreatedAt(penyuluh.getCreatedAt());
        dto.setUpdatedAt(penyuluh.getUpdatedAt());
        return dto;
    }

    @Override
    public List<Long> getListKiosId(Long penyuluhUserId) {
        Penyuluh penyuluh = penyuluhRepository.findByUserId(penyuluhUserId);
        return penyuluh.getKiosList().stream().map(Kios::getKiosId).toList();
    }

    @Override
    public void create(PenyuluhCreateUpdateRequestDTO dto) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setPassword(dto.getPassword());
        createUserRequest.setFileGambar("avatar.png");
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setRole("PENYULUH");
        createUserRequest.setUsername(dto.getUsername());
        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        Penyuluh penyuluh = new Penyuluh();
        penyuluh.setUserId(createUserResponse.getUserId());
        penyuluh.setAlamat(dto.getAlamat());
        penyuluh.setVerifikasi(dto.isVerifikasi());
        penyuluh.setCreatedAt(LocalDate.now());
        penyuluh.setUpdatedAt(LocalDate.now());
        penyuluhRepository.save(penyuluh);

        dto.getKios().forEach(kios->{
            CreateUserResponse userResponse = userCreateClient.searchUserByNama((String) kios);
            Kios k = kiosRepository.findByUserId(userResponse.getUserId());
            k.setPenyuluh(penyuluh);
            kiosRepository.save(k);
        });
    }

    @Override
    public void update(Long penyuluhId, PenyuluhCreateUpdateRequestDTO dto) {
        Penyuluh penyuluh = penyuluhRepository.findById(penyuluhId).orElseThrow(()->new ResourceNotFoundException("penyuluh.not.found"));
        penyuluh.setAlamat(dto.getAlamat());
        penyuluh.setVerifikasi(!dto.isVerifikasi() || dto.isVerifikasi());
        penyuluh.setUpdatedAt(LocalDate.now());
        penyuluhRepository.save(penyuluh);
    }

    @Override
    public void updateKios(Long penyuluhId, Long kiosId) {
        Kios kios = kiosRepository.findById(kiosId).orElseThrow(()->new ResourceNotFoundException("kios.not.found"));
        List<Kios> kiosList = new ArrayList<>();
        kiosList.add(kios);

        Penyuluh penyuluh = penyuluhRepository.findById(penyuluhId).orElseThrow(()->new ResourceNotFoundException("penyuluh.not.found"));
        penyuluh.setKiosList(kiosList);
        penyuluhRepository.save(penyuluh);
    }

    @Override
    public void delete(Long penyuluhId) {
        penyuluhRepository.deleteById(penyuluhId);
    }
}
