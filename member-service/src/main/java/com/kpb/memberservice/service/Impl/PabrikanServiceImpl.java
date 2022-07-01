package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.*;
import com.kpb.clientservice.web.Member.PabrikanResponse;
import com.kpb.memberservice.domain.Distributor;
import com.kpb.memberservice.domain.Kios;
import com.kpb.memberservice.domain.Pabrikan;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.distributor.DistributorListResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanDetailResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanListResponseDTO;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.PabrikanRepository;
import com.kpb.memberservice.service.PabrikanService;
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
import java.util.stream.Collectors;

@Service
public class PabrikanServiceImpl implements PabrikanService {

    @Autowired
    private PabrikanRepository pabrikanRepository;

    @Autowired
    private UserCreateClient userCreateClient;


    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "pabrikanId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Pabrikan> pabrikan = pabrikanRepository.findAllByJenisPabrikLike(search, pageable);

        List<PabrikanListResponseDTO> pabrikanDetailResponseDTOS = pabrikan.stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            PabrikanListResponseDTO dto = new PabrikanListResponseDTO();
            dto.setPabrikanId(b.getPabrikanId());
            dto.setNama(createUserResponse.getNama());
            dto.setJenisPabrik(b.getJenisPabrik());
            dto.setAlamat(b.getAlamat());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(pabrikanDetailResponseDTOS);
        paginationResponse.setEmpty(pabrikan.isEmpty());
        paginationResponse.setFirst(pabrikan.isFirst());
        paginationResponse.setLast(pabrikan.isLast());
        paginationResponse.setNumber(pabrikan.getNumber());
        paginationResponse.setPageable(pabrikan.getPageable());
        paginationResponse.setSize(pabrikan.getSize());
        paginationResponse.setSort(pabrikan.getSort());
        paginationResponse.setTotalElements(pabrikan.getTotalElements());
        paginationResponse.setNumberOfElements(pabrikan.getNumberOfElements());
        paginationResponse.setTotalPages(pabrikan.getTotalPages());
        return paginationResponse;
    }

    @Override
    public List<PabrikanListResponseDTO> findAllWithoutPagination() {
        List<Pabrikan> pabrikan = pabrikanRepository.findAll();

        return pabrikan.stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            PabrikanListResponseDTO dto = new PabrikanListResponseDTO();
            dto.setPabrikanId(b.getPabrikanId());
            dto.setNama(createUserResponse.getNama());
            dto.setAlamat(b.getAlamat());
            dto.setJenisPabrik(b.getJenisPabrik());
            return dto;
        }).toList();
    }

    @Override
    public List<Long> findListKiosByPabrikanUserId(Long pabrikanUserId) {
        Pabrikan pabrikan = pabrikanRepository.findByUserId(pabrikanUserId);

        List<Long> temp = new ArrayList<>();

        pabrikan.getDistributor().forEach(dis->{
            List<Long> listKios = dis.getKiosList().stream().map(Kios::getKiosId).toList();
            temp.addAll(listKios);
        });

        return temp;
    }

    @Override
    public PabrikanDetailResponseDTO findDetail(Long pabrikanId) {
        Pabrikan pabrikan = pabrikanRepository.findById(pabrikanId).orElseThrow(() -> new ResourceNotFoundException("pabrikan.not.found"));
        CreateUserResponse createUserResponse = userCreateClient.getUser(pabrikan.getUserId());
        List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(pabrikan.getUserId());
        List<AkunBankResponse> akunBankResponse = userCreateClient.getAkunBank(pabrikan.getUserId());

        List<DistributorListResponseDTO> responseDTO = pabrikan.getDistributor().stream().map(b -> {
            CreateUserResponse userResponse = userCreateClient.getUser(b.getUserId());
            DistributorListResponseDTO distributorListResponseDTO = new DistributorListResponseDTO();
            distributorListResponseDTO.setDistributorId(b.getDistributorId());
            distributorListResponseDTO.setNama(userResponse.getNama());
            distributorListResponseDTO.setNomorAhu(b.getNomorAhu());
            distributorListResponseDTO.setJenisDistributor(b.getJenisDistributor());
            distributorListResponseDTO.setAlamat(b.getAlamat());
            distributorListResponseDTO.setWorkingAreaDistributors(b.getWorkingAreaDistributors());
            distributorListResponseDTO.setStatus(b.getStatus());
            return distributorListResponseDTO;
        }).toList();

        PabrikanDetailResponseDTO dto = new PabrikanDetailResponseDTO();
        dto.setPabrikanId(pabrikan.getPabrikanId());
        dto.setNama(createUserResponse.getNama());
        dto.setFileGambar(createUserResponse.getFileGambar());
        dto.setEmail(createUserResponse.getEmail());
        dto.setUsername(createUserResponse.getUsername());
        dto.setNomorTelepon(createUserResponse.getNomorTelepon());
        dto.setJenisPabrik(pabrikan.getJenisPabrik());
        dto.setAlamat(pabrikan.getAlamat());
        dto.setDistributor(responseDTO);
        dto.setCreatedAt(String.valueOf(pabrikan.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(pabrikan.getUpdatedAt()));
        dto.setArea(areaResponse);
        dto.setAkunBank(akunBankResponse);
        return dto;
    }

    @Override
    public PabrikanResponse findDetailByUserId(Long pabrikanUserId) {
        Pabrikan pabrikan = pabrikanRepository.findByUserId(pabrikanUserId);
        CreateUserResponse userResponse = userCreateClient.getUser(pabrikan.getUserId());

        PabrikanResponse pabrikanResponse = new PabrikanResponse();
        pabrikanResponse.setPabrikanId(pabrikan.getPabrikanId());
        pabrikanResponse.setNama(userResponse.getNama());
        pabrikanResponse.setFileGambar(userResponse.getFileGambar());
        pabrikanResponse.setEmail(userResponse.getEmail());
        pabrikanResponse.setUsername(userResponse.getUsername());
        pabrikanResponse.setNomorTelepon(userResponse.getNomorTelepon());
        pabrikanResponse.setJenisPabrik(pabrikan.getJenisPabrik());
        pabrikanResponse.setAlamat(pabrikan.getAlamat());
        pabrikanResponse.setCreatedAt(String.valueOf(pabrikan.getCreatedAt()));
        pabrikanResponse.setUpdatedAt(String.valueOf(pabrikan.getUpdatedAt()));
        return pabrikanResponse;
    }

    @Override
    public void create(PabrikanCreateUpdateRequestDTO dto) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setPassword(dto.getPassword());
        createUserRequest.setFileGambar("avatar.png");
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setRole("POKTAN");
        createUserRequest.setUsername(dto.getUsername());
        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        Pabrikan pabrikan = new Pabrikan();
        pabrikan.setUserId(createUserResponse.getUserId());
        pabrikan.setAlamat(dto.getAlamat());
        pabrikan.setJenisPabrik(dto.getJenisPabrik());
        pabrikan.setCreatedAt(LocalDate.now());
        pabrikan.setUpdatedAt(LocalDate.now());
        pabrikanRepository.save(pabrikan);
    }

    @Override
    public void update(Long pabrikanId, PabrikanCreateUpdateRequestDTO dto) {
        Pabrikan pabrikan = pabrikanRepository.findById(pabrikanId).orElseThrow(()->new ResourceNotFoundException("pabrikan.not.found"));
        pabrikan.setAlamat(dto.getAlamat());
        pabrikan.setJenisPabrik(dto.getJenisPabrik());
        pabrikan.setUpdatedAt(LocalDate.now());
        pabrikanRepository.save(pabrikan);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setUsername(dto.getUsername());
        userCreateClient.updateUser(pabrikan.getUserId(), createUserRequest);
    }

    @Override
    public void delete(Long pabrikanId) {
        pabrikanRepository.deleteById(pabrikanId);
    }
}
