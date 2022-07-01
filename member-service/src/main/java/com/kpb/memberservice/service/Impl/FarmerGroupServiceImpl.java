package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.*;
import com.kpb.clientservice.web.Member.PoktanResponse;
import com.kpb.memberservice.domain.*;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.FarmerListResponseDTO;
import com.kpb.memberservice.dto.farmerGroup.*;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.*;
import com.kpb.memberservice.service.FarmerGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service @Slf4j
public class FarmerGroupServiceImpl implements FarmerGroupService {

    @Autowired
    private PoktanRepository poktanRepository;

    @Autowired
    private PetaniRepository petaniRepository;

    @Autowired
    private KiosRepository kiosRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private PenyuluhRepository penyuluhRepository;

    @Override
    public PaginationResponse findAllFarmerGroups(int page, int size, String sort, String search, Long kios) {
        sort = (sort != null) ? sort : "poktanId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Poktan> farmerGroups;

        if(!Objects.isNull(kios)) {
            farmerGroups = poktanRepository.findAllByNamaPoktanLikeAndKios_UserId(search, kios, pageable);
        } else {
            farmerGroups = poktanRepository.findAllByNamaPoktanLike(search, pageable);
        }
        return this.paginationResponse(farmerGroups);
    }

    @Override
    public PaginationResponse findAllFarmGroupsByPenyuluh(int page, int size, String sort, String search, String role, Long userId) {
        sort = (sort != null) ? sort : "poktanId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Poktan> farmerGroups;

        if(role.equals("penyuluh")) {
            Penyuluh penyuluh = penyuluhRepository.findByUserId(userId);
            List<Long> listKios = penyuluh.getKiosList().stream().map(Kios::getKiosId).toList();
            farmerGroups = poktanRepository.findAllByNamaPoktanLikeAndKios_KiosIdIn(search, listKios, pageable);
        } else {
            farmerGroups = poktanRepository.findAllByNamaPoktanLike(search, pageable);
        }
        return this.paginationResponse(farmerGroups);
    }

    public PaginationResponse paginationResponse(Page<Poktan> farmerGroups) {
        List<FarmerGroupListResponseDTO> farmerGroupListResponseDTOS = farmerGroups.stream().map((b) -> {
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(b.getUserId());
            AreaResponse area = new AreaResponse();
            if(areaResponse.size() > 0) area = areaResponse.get(0);

            FarmerGroupListResponseDTO response = new FarmerGroupListResponseDTO();
            response.setPoktanId(b.getPoktanId());
            response.setUserId(b.getUserId());
            response.setNamaPoktan(b.getNamaPoktan());
            response.setDesa(area.getNamaDesa());
            response.setKecamatan(area.getNamaKecamatan());
            response.setKabupaten(area.getNamaKabupaten());
            return response;
        }).collect(Collectors.toList());

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(farmerGroupListResponseDTOS);
        paginationResponse.setEmpty(farmerGroups.isEmpty());
        paginationResponse.setFirst(farmerGroups.isFirst());
        paginationResponse.setLast(farmerGroups.isLast());
        paginationResponse.setNumber(farmerGroups.getNumber());
        paginationResponse.setPageable(farmerGroups.getPageable());
        paginationResponse.setSize(farmerGroups.getSize());
        paginationResponse.setSort(farmerGroups.getSort());
        paginationResponse.setTotalElements(farmerGroups.getTotalElements());
        paginationResponse.setNumberOfElements(farmerGroups.getNumberOfElements());
        paginationResponse.setTotalPages(farmerGroups.getTotalPages());
        return paginationResponse;
    }

    @Override
    public List<FarmerListResponseDTO> findAllFarmerLinked(Long poktanId) {
        Poktan poktan = poktanRepository.findById(poktanId).orElseThrow(()->new ResourceNotFoundException("poktan.not.found"));

        return poktan.getPetani().stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            FarmerListResponseDTO dto = new FarmerListResponseDTO();
            dto.setPetaniId(b.getPetaniId());
            dto.setNama(createUserResponse.getNama());
            dto.setNomorTelepon(createUserResponse.getNomorTelepon());
            dto.setStatusPetani(b.getStatusPetani());
            dto.setNik(b.getNik());
            dto.setStatusPetani(b.getStatusPetani());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<FarmerGroupListResponseDTO> findAllPoktanByArea(String provinsi, String kabupaten, String kecamatan, String desa) {
        List<Long> listUserIdFromArea = userCreateClient.getUserIdInAreaByForm(provinsi,kabupaten,kecamatan,desa);
        List<Poktan> poktan = poktanRepository.findAllByUserIdIn(listUserIdFromArea);

        return poktan.stream().map(b->{
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(b.getUserId());
            AreaResponse area = new AreaResponse();
            if(areaResponse.size() > 0) area = areaResponse.get(0);

            FarmerGroupListResponseDTO dto = new FarmerGroupListResponseDTO();
            dto.setPoktanId(b.getPoktanId());
            dto.setUserId(b.getUserId());
            dto.setStatusKonfirmasi(b.isStatusKonfirmasi());
            dto.setNamaPoktan(b.getNamaPoktan());
            dto.setDesa(b.getNamaDesa());
            dto.setKecamatan(area.getNamaKecamatan());
            dto.setKabupaten(area.getNamaKabupaten());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Long> findListPetaniByPoktanUserId(Long poktanUserId) {
        Poktan poktan= poktanRepository.findByUserId(poktanUserId);
        return poktan.getPetani().stream().map(b-> {
            return b.getPetaniId();
        }).toList();
    }

    @Override
    public List<FarmerGroupListResponseDTO> findAllFarmerGroupsWithoutPagination() {
        List<Poktan> poktan = poktanRepository.findAll();

        return poktan.stream().map(b->{
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(b.getUserId());
            AreaResponse area = new AreaResponse();
            if(areaResponse.size() > 0) area = areaResponse.get(0);

            FarmerGroupListResponseDTO dto = new FarmerGroupListResponseDTO();
            dto.setPoktanId(b.getPoktanId());
            dto.setUserId(b.getUserId());
            dto.setStatusKonfirmasi(b.isStatusKonfirmasi());
            dto.setNamaPoktan(b.getNamaPoktan());
            dto.setDesa(b.getNamaDesa());
            dto.setKecamatan(area.getNamaKecamatan());
            dto.setKabupaten(area.getNamaKabupaten());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addFarmerToGroup(Long farmerGroupId, FarmerGroupAddFarmerRequestDTO dto) {
        Poktan poktan = poktanRepository.findById(farmerGroupId).orElseThrow(()->new ResourceNotFoundException("farmerGroup.not.found"));
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername(dto.getUsername());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setPassword(dto.getNik());
        createUserRequest.setFileGambar("avatar.png");
        createUserRequest.setRole("PETANI");
        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        Petani petani = new Petani();
        petani.setPoktan(poktan);
        petani.setUserId(createUserResponse.getUserId());
        petani.setIbuKandung(dto.getIbuKandung());
        petani.setStatusPetani(dto.getStatusPetani());
        petani.setJenisKelamin(dto.getJenisKelamin());
        petani.setNik(dto.getNik());
        petani.setAlamat(dto.getAlamat());
        petani.setCreatedAt(LocalDate.now());
        petani.setUpdatedAt(LocalDate.now());
        petaniRepository.save(petani);
    }

    @Override
    public void addFarmerToFarmerGroupByUserPoktan(Long userFarmerGroupId, FarmerGroupAddFarmerRequestDTO dto) {
        Poktan poktan = poktanRepository.findByUserId(userFarmerGroupId);
        this.addFarmerToGroup(poktan.getPoktanId(), dto);
    }

    @Override
    public FarmerGroupDetailResponseDTO findFarmerGroup(Long farmerGroupId) {
        Poktan poktan = poktanRepository.findById(farmerGroupId).orElseThrow(()->new ResourceNotFoundException("farmerGroup.not.found"));
        CreateUserResponse createUserResponse = userCreateClient.getUser(poktan.getUserId());
        List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(poktan.getUserId());
        AreaResponse area = new AreaResponse();
        if(areaResponse.size() > 0) area = areaResponse.get(0);
        List<AkunBankResponse> akunBankResponseList = userCreateClient.getAkunBank(poktan.getUserId());

        CreateUserResponse kiosResponse = userCreateClient.getUser(poktan.getKios().getUserId());
        KiosListResponseDTO kiosListResponseDTO = new KiosListResponseDTO();
        kiosListResponseDTO.setKiosId(poktan.getKios().getKiosId());
        kiosListResponseDTO.setNamaKomoditas(poktan.getKios().getNamaKomoditas());
        kiosListResponseDTO.setKodePihc(poktan.getKios().getKodePihc());
        kiosListResponseDTO.setVerifikasiPasarBebas(poktan.getKios().getVerifikasiPasarBebas());
        kiosListResponseDTO.setNama(kiosResponse.getNama());
        kiosListResponseDTO.setUsername(kiosResponse.getUsername());

        FarmerGroupDetailResponseDTO response = new FarmerGroupDetailResponseDTO();
        response.setPoktanId(poktan.getPoktanId());
        response.setNamaPoktan(poktan.getNamaPoktan());
        response.setNamaKetuaPoktan(poktan.getNamaKetua());
        response.setRegistrasi(poktan.isRegistered());
        response.setStatusKonfirmasi(poktan.isStatusKonfirmasi());
        response.setJenisPoktan(poktan.getJenisPoktan());
        response.setNomorTelepon(createUserResponse.getNomorTelepon());
        response.setFileGambar(createUserResponse.getFileGambar());
        response.setEmail(createUserResponse.getEmail());
        response.setUsernamePoktan(createUserResponse.getUsername());
        response.setAlamat(poktan.getAlamat());
        response.setDesa(area.getNamaDesa());
        response.setKecamatan(area.getNamaKecamatan());
        response.setKabupaten(area.getNamaKabupaten());
        response.setProvinsi(area.getNamaProvinsi());
        response.setCreatedAt(String.valueOf(poktan.getCreatedAt()));
        response.setKios(kiosListResponseDTO);
        response.setUpdatedAt(String.valueOf(poktan.getUpdatedAt()));
        response.setAkunBankResponseList(akunBankResponseList);
        return response;
    }

    @Override
    public PoktanResponse findFarmerGroupByPoktanId(Long poktanId) {
        Poktan poktan = poktanRepository.findById(poktanId).orElseThrow(()->new ResourceNotFoundException("poktan.not.found"));
        CreateUserResponse userResponse = userCreateClient.getUser(poktan.getUserId());
        return this.poktanResponse(poktan, userResponse);
    }

    @Override
    public PoktanResponse findFarmerGroupByUserId(Long poktanUserId) {
        Poktan poktan = poktanRepository.findByUserId(poktanUserId);
        CreateUserResponse userResponse = userCreateClient.getUser(poktan.getUserId());
        return this.poktanResponse(poktan, userResponse);
    }

    public PoktanResponse poktanResponse(Poktan poktan, CreateUserResponse userResponse) {
        PoktanResponse poktanResponse = new PoktanResponse();
        poktanResponse.setPoktanId(poktan.getPoktanId());
        poktanResponse.setUserId(poktan.getUserId());
        poktanResponse.setNamaPoktan(poktan.getNamaPoktan());
        poktanResponse.setEmail(userResponse.getEmail());
        poktanResponse.setUsernamePoktan(userResponse.getUsername());
        poktanResponse.setNomorTelepon(userResponse.getNomorTelepon());
        poktanResponse.setFileGambar(userResponse.getFileGambar());
        poktanResponse.setJenisPoktan(poktan.getJenisPoktan());
        poktanResponse.setNamaKetuaPoktan(poktan.getNamaKetua());
        poktanResponse.setRegistrasi(poktan.isRegistered());
        poktanResponse.setStatusKonfirmasi(poktan.isStatusKonfirmasi());
        poktanResponse.setAlamat(poktan.getAlamat());
        poktanResponse.setCreatedAt(String.valueOf(poktan.getCreatedAt()));
        poktanResponse.setUpdatedAt(String.valueOf(poktan.getUpdatedAt()));
        return poktanResponse;
    }

    @Override
    public FarmerGroupDetailResponseDTO findFarmerGroupByPetani(Long user) {
        Petani petani = petaniRepository.findByUserId(user);
        return this.findFarmerGroup(petani.getPoktan().getPoktanId());
    }

    @Override
    public void createFarmerGroup(FarmerGroupCreateRequestDTO dto) {

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNamaPoktan());
        createUserRequest.setPassword(dto.getPasswordPoktan());
        createUserRequest.setFileGambar("avatar.png");
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setRole("POKTAN");
        createUserRequest.setUsername(dto.getUsernamePoktan());

        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        AreaRequest areaRequest = new AreaRequest();
        areaRequest.setKodeDesa(dto.getKodeDesa());
        areaRequest.setNamaDesa(dto.getDesa());
        areaRequest.setKodeKecamatan(dto.getKodeKecamatan());
        areaRequest.setNamaKecamatan(dto.getKecamatan());
        areaRequest.setKodeKabupaten(dto.getKodeKabupaten());
        areaRequest.setNamaKabupaten(dto.getKabupaten());
        areaRequest.setKodeProvinsi(dto.getKodeProvinsi());
        areaRequest.setNamaProvinsi(dto.getProvinsi());
        AreaResponse areaResponse = userCreateClient.createArea(createUserResponse.getUserId(), areaRequest);

        Poktan poktan = new Poktan();
        poktan.setNamaPoktan(dto.getNamaPoktan());
        poktan.setJenisPoktan(dto.getJenisPoktan());
        poktan.setNamaKetua(dto.getNamaKetuaPoktan());
        poktan.setAlamat(dto.getAlamat());
        poktan.setNamaDesa(dto.getDesa());
        poktan.setKodeDesa(dto.getKodeDesa());
        poktan.setStatusKonfirmasi(true);
        poktan.setRegistered(true);
        poktan.setUserId(createUserResponse.getUserId());
        poktan.setCreatedAt(LocalDate.now());
        poktan.setUpdatedAt(LocalDate.now());
        poktanRepository.save(poktan);
    }

    @Override
    public FarmerGroupDetailResponseDTO updateFarmerGroup(Long farmerGroupId, FarmerGroupUpdateRequestDTO dto) {
        Poktan poktan = poktanRepository.findById(farmerGroupId).orElseThrow(()->new ResourceNotFoundException("farmerGroup.not.found"));
        poktan.setNamaPoktan(dto.getNamaPoktan());
        poktan.setJenisPoktan(dto.getJenisPoktan());
        poktan.setNamaKetua(dto.getNamaKetuaPoktan());
        poktan.setAlamat(dto.getAlamat());
        poktan.setKodeDesa(dto.getKodeDesa());
        poktan.setNamaDesa(dto.getDesa());
        poktan.setUpdatedAt(LocalDate.now());
        poktanRepository.save(poktan);

        List<AreaResponse> areaResponse= userCreateClient.getAreaByUserId(poktan.getUserId());
        log.info(String.valueOf(areaResponse));
        AreaResponse area = new AreaResponse();
        AreaRequest areaRequest = new AreaRequest();
        areaRequest.setNamaDesa(dto.getDesa());
        areaRequest.setNamaKabupaten(dto.getKabupaten());
        areaRequest.setNamaKecamatan(dto.getKecamatan());
        areaRequest.setNamaProvinsi(dto.getProvinsi());
        areaRequest.setKodeDesa(21);
        areaRequest.setKodeKabupaten(22);
        areaRequest.setKodeKecamatan(32);
        areaRequest.setKodeProvinsi(43);
        if(areaResponse.size() > 0) {
            area = areaResponse.get(0);
            userCreateClient.updateArea(area.getAreaId(), areaRequest);
        } else {
            userCreateClient.createArea(poktan.getUserId(), areaRequest);
        }

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNamaPoktan());
        createUserRequest.setPassword(dto.getPasswordPoktan());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setUsername(dto.getUsernamePoktan());
        CreateUserResponse createUserResponse = userCreateClient.updateUser(poktan.getUserId(), createUserRequest);

        return this.findFarmerGroup(farmerGroupId);
    }

    @Override
    public void deleteFarmerGroup(Long farmerGroupId) {
        Poktan poktan = poktanRepository.findById(farmerGroupId).orElseThrow(()->new ResourceNotFoundException("farmerGroup.not.found"));
        userCreateClient.deleteUser(poktan.getUserId());
        poktanRepository.deleteById(farmerGroupId);
    }
}
