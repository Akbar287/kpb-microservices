package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.memberservice.domain.*;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.distributor.*;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.dto.pabrikan.PabrikanListResponseDTO;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.*;
import com.kpb.memberservice.service.DistributorService;
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
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private KiosRepository kiosRepository;

    @Autowired
    private PabrikanRepository pabrikanRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private WilayahKerjaDistributorRepository wilayahKerjaDistributorRepository;

    @Autowired
    private PetaniRepository petaniRepository;

    @Autowired
    private PoktanRepository poktanRepository;

    @Autowired
    private PenyuluhRepository penyuluhRepository;

    @Override
    public PaginationResponse findAllDistributor(int page, int size, String sort, String search, String role, Long userId) {
        sort = (sort != null) ? sort : "distributorId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Distributor> distributors = null;

        Pabrikan pabrikan = null;
        List<Long> listDistributor = null;
        if(role.equals("pabrikan")) {
            pabrikan= pabrikanRepository.findByUserId(userId);
        } else if (role.equals("penyuluh")) {
            Penyuluh penyuluh = penyuluhRepository.findByUserId(userId);
             listDistributor = penyuluh.getKiosList().stream().map(kios->kios.getDistributor().getDistributorId()).toList();
        }

        if(role.equals("pabrikan")) {
            distributors = distributorRepository.findAllByNomorAhuLikeAndPabrikan_PabrikanId(search, pabrikan.getPabrikanId(), pageable);
        } else if(role.equals("penyuluh")) {
            distributors = distributorRepository.findAllByNomorAhuLikeAndDistributorIdIn(search, listDistributor, pageable);
        } else {
            distributors = distributorRepository.findAllByNomorAhuLike(search, pageable);
        }

        List<DistributorListResponseDTO> distributorListResponseDTOS = distributors.stream().map((distributor)-> {
            List<WilayahKerjaDistributor> workingAreaDistributors = distributor.getWorkingAreaDistributors();
            CreateUserResponse createUserResponse = userCreateClient.getUser(distributor.getUserId());
            DistributorListResponseDTO dto = new DistributorListResponseDTO();
            dto.setDistributorId(distributor.getDistributorId());
            dto.setNama(createUserResponse.getNama());
            dto.setAlamat(distributor.getAlamat());
            dto.setNomorAhu(distributor.getNomorAhu());
            dto.setJenisDistributor(distributor.getJenisDistributor());
            dto.setStatus(distributor.getStatus());
            dto.setWorkingAreaDistributors(workingAreaDistributors);
            return dto;
        }).collect(Collectors.toList());

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(distributorListResponseDTOS);
        paginationResponse.setEmpty(distributors.isEmpty());
        paginationResponse.setFirst(distributors.isFirst());
        paginationResponse.setLast(distributors.isLast());
        paginationResponse.setNumber(distributors.getNumber());
        paginationResponse.setPageable(distributors.getPageable());
        paginationResponse.setSize(distributors.getSize());
        paginationResponse.setSort(distributors.getSort());
        paginationResponse.setTotalElements(distributors.getTotalElements());
        paginationResponse.setNumberOfElements(distributors.getNumberOfElements());
        paginationResponse.setTotalPages(distributors.getTotalPages());
        return paginationResponse;
    }

    @Override
    public DistributorDetailResponseDTO findDetailDistributor(Long distributorId) {
        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(()->new ResourceNotFoundException("distributor.not.found"));
        CreateUserResponse createUserResponse = userCreateClient.getUser(distributor.getUserId());
        List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(createUserResponse.getUserId());
        List<AkunBankResponse> akunBankResponse = userCreateClient.getAkunBank(createUserResponse.getUserId());

        CreateUserResponse userPabrikan = null;
        PabrikanListResponseDTO pabrikan = null;

        if(distributor.getPabrikan() != null) {
            userPabrikan = userCreateClient.getUser(distributor.getPabrikan().getUserId());
            pabrikan = new PabrikanListResponseDTO();
            pabrikan.setPabrikanId(distributor.getPabrikan().getPabrikanId());
            pabrikan.setJenisPabrik(distributor.getPabrikan().getJenisPabrik());
            pabrikan.setAlamat(distributor.getPabrikan().getAlamat());
            pabrikan.setNama(userPabrikan.getNama());
        }

        List<KiosListResponseDTO> kiosListResponseDTOS = distributor.getKiosList().stream().map(b->{
            CreateUserResponse userKios = userCreateClient.getUser(b.getUserId());
            KiosListResponseDTO kiosListResponseDTO = new KiosListResponseDTO();
            kiosListResponseDTO.setKiosId(b.getKiosId());
            kiosListResponseDTO.setNamaKomoditas(b.getNamaKomoditas());
            kiosListResponseDTO.setNama(userKios.getNama());
            kiosListResponseDTO.setKodePihc(b.getKodePihc());
            kiosListResponseDTO.setVerifikasiPasarBebas(b.getVerifikasiPasarBebas());
            kiosListResponseDTO.setUsername(userKios.getUsername());
            return kiosListResponseDTO;
        }).toList();

        DistributorDetailResponseDTO dto = new DistributorDetailResponseDTO();
        dto.setDistributorId(distributor.getDistributorId());
        dto.setNama(createUserResponse.getNama());
        dto.setEmail(createUserResponse.getEmail());
        dto.setUsername(createUserResponse.getUsername());
        dto.setNomorTelepon(createUserResponse.getNomorTelepon());
        dto.setFileGambar(createUserResponse.getFileGambar());
        dto.setAlamat(distributor.getAlamat());
        dto.setNomorAhu(distributor.getNomorAhu());
        dto.setJenisDistributor(distributor.getJenisDistributor());
        dto.setStatus(distributor.getStatus());
        dto.setCreatedAt(String.valueOf(distributor.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(distributor.getUpdatedAt()));
        dto.setArea(areaResponse);
        dto.setPabrikan(pabrikan);
        dto.setAkunBank(akunBankResponse);
        dto.setKios(kiosListResponseDTOS);
        dto.setWilayahKerjaDistributors(distributor.getWorkingAreaDistributors());
        return dto;
    }

    @Override
    public DistributorResponse findDetailDistributorByKiosFromUser(Long user) {
        Kios kios = kiosRepository.findByUserId(user);
        CreateUserResponse userResponse = userCreateClient.getUser(kios.getDistributor().getUserId());
        return this.distributorResponse(kios.getDistributor(), userResponse);
    }

    @Override
    public DistributorResponse findDetailDistributorByPetaniFromUser(Long user) {
        Petani petani = petaniRepository.findByUserId(user);
        CreateUserResponse userResponse = userCreateClient.getUser(user);
        return this.distributorResponse(petani.getPoktan().getKios().getDistributor(), userResponse);
    }

    @Override
    public DistributorResponse findDetailDistributorByPoktanFromUser(Long user) {
        Poktan poktan = poktanRepository.findByUserId(user);
        CreateUserResponse userResponse = userCreateClient.getUser(user);
        return this.distributorResponse(poktan.getKios().getDistributor(), userResponse);
    }

    public DistributorResponse distributorResponse(Distributor distributor, CreateUserResponse userResponse) {
        DistributorResponse response = new DistributorResponse();
        response.setDistributorId(distributor.getDistributorId());
        response.setNama(userResponse.getNama());
        response.setEmail(userResponse.getEmail());
        response.setUsername(userResponse.getUsername());
        response.setNomorTelepon(userResponse.getNomorTelepon());
        response.setFileGambar(userResponse.getFileGambar());
        response.setAlamat(distributor.getAlamat());
        response.setNomorAhu(distributor.getNomorAhu());
        response.setJenisDistributor(distributor.getJenisDistributor());
        response.setStatus(distributor.getStatus());
        response.setCreatedAt(String.valueOf(distributor.getCreatedAt()));
        response.setUpdatedAt(String.valueOf(distributor.getUpdatedAt()));
        return response;
    }

    @Override
    public List<DistributorListResponseDTO> findAllDistributorWithoutPagination() {
        List<Distributor> distributor = distributorRepository.findAll();

        return distributor.stream().map(b->{
            CreateUserResponse userResponse = userCreateClient.getUser(b.getUserId());
            DistributorListResponseDTO dto = new DistributorListResponseDTO();
            dto.setDistributorId(b.getDistributorId());
            dto.setNama(userResponse.getNama());
            dto.setJenisDistributor(b.getJenisDistributor());
            dto.setNomorAhu(b.getNomorAhu());
            dto.setAlamat(b.getAlamat());
            dto.setStatus(b.getStatus());
            dto.setWorkingAreaDistributors(b.getWorkingAreaDistributors());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Long> findAllKiosIdByDistributorUserId(Long distributorUserId) {
        Distributor distributor = distributorRepository.findByUserId(distributorUserId);

        return distributor.getKiosList().stream().map(Kios::getKiosId).toList();
    }

    @Override
    public void createDistributor(DistributorCreateRequestDTO dto) {

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setPassword(dto.getPassword());
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setFileGambar(dto.getFileGambar());
        createUserRequest.setRole("DISTRIBUTOR");
        createUserRequest.setUsername(dto.getUsername());
        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        Distributor distributor = new Distributor();
        distributor.setUserId(createUserResponse.getUserId());
        distributor.setAlamat(dto.getAlamat());
        distributor.setNomorAhu(dto.getNomorAhu());
        distributor.setJenisDistributor(dto.getJenisDistributor());
        distributor.setStatus(1);
        distributor.setCreatedAt(LocalDate.now());
        distributor.setUpdatedAt(LocalDate.now());
        distributorRepository.save(distributor);
    }

    @Override
    public void updateDistributor(Long distributorId, DistributorUpdateRequestDTO dto) {

        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(()->new ResourceNotFoundException("distributor.not.found"));
        distributor.setAlamat(dto.getAlamat());
        distributor.setNomorAhu(dto.getNomorAhu());
        distributor.setJenisDistributor(dto.getJenisDistributor());
        distributor.setStatus(2);
        distributor.setUpdatedAt(LocalDate.now());
        distributorRepository.save(distributor);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername(dto.getUsername());
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        userCreateClient.updateUser(distributor.getUserId(), createUserRequest);

        this.findDetailDistributor(distributor.getDistributorId());
    }

    @Override
    public void updatePabrikan(Long distributorId, Long pabrikanId) {
        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(()->new ResourceNotFoundException("distributor.not.found"));
        Pabrikan pabrikan = pabrikanRepository.findById(pabrikanId).orElseThrow(()->new ResourceNotFoundException("pabrikan.not.found"));

        distributor.setPabrikan(pabrikan);
        distributorRepository.save(distributor);
    }

    @Override
    public void updateDistributorWorkingArea(Long distributorId, List<DistributorWorkingAreaCreateUpdateDTO> dto) {
        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(()->new ResourceNotFoundException("distributor.not.found"));

        List<WilayahKerjaDistributor> workingAreaDistributors = distributor.getWorkingAreaDistributors();
        if(workingAreaDistributors.size() > 0) {
            List<Long> oldArea = workingAreaDistributors.stream().map(WilayahKerjaDistributor::getWilayahKerjaDistributorId).toList();
            wilayahKerjaDistributorRepository.deleteAllById(oldArea);
            workingAreaDistributors.clear();
        }

        workingAreaDistributors = dto.stream().map((area)->{
            WilayahKerjaDistributor workingAreaDistributor = new WilayahKerjaDistributor();
            workingAreaDistributor.setKodeProvinsi(area.getKodeProvinsi());
            workingAreaDistributor.setNamaProvinsi(area.getNamaProvinsi());
            workingAreaDistributor.setKodeKabupaten(area.getKodeKabupaten());
            workingAreaDistributor.setNamaKabupaten(area.getNamaKabupaten());
            workingAreaDistributor.setKodeKecamatan(area.getKodeKecamatan());
            workingAreaDistributor.setNamaKecamatan(area.getNamaKecamatan());
            workingAreaDistributor.setKodeDesa(area.getKodeDesa());
            workingAreaDistributor.setNamaDesa(area.getNamaDesa());
            return workingAreaDistributor;
        }).collect(Collectors.toList());
        wilayahKerjaDistributorRepository.saveAll(workingAreaDistributors);

        distributor.setWorkingAreaDistributors(workingAreaDistributors);
        distributorRepository.save(distributor);
    }

    @Override
    public void deleteDistributor(Long distributorId) {
        distributorRepository.deleteById(distributorId);
    }
}
