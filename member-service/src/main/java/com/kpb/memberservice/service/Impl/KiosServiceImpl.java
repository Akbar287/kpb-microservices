package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.memberservice.domain.*;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.distributor.DistributorListResponseDTO;
import com.kpb.memberservice.dto.farmerGroup.FarmerGroupListResponseDTO;
import com.kpb.memberservice.dto.kios.KiosCreateUpdateRequestDTO;
import com.kpb.memberservice.dto.kios.KiosListResponseDTO;
import com.kpb.memberservice.dto.kios.KiosResponseDTO;
import com.kpb.memberservice.dto.kios.KiosWorkingAreaCreateUpdateDTO;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.*;
import com.kpb.memberservice.service.KiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class KiosServiceImpl implements KiosService {

    @Autowired
    private KiosRepository kiosRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private WilayahKerjaKiosRepository wilayahKerjaKiosRepository;

    @Autowired
    private PetaniRepository petaniRepository;

    @Autowired
    private PoktanRepository poktanRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort,String search, Long userId, String role) {
        sort = (sort != null) ? sort : "kiosId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Kios> kios;

        if(role.equals("distributor")) {
            kios = kiosRepository.findAllByKodePihcLikeAndDistributor_UserId(search, userId, pageable);
        } else if(role.equals("penyuluh")) {
            kios = kiosRepository.findAllByKodePihcLikeAndPenyuluh_UserId(search, userId, pageable);
        } else {
            kios = kiosRepository.findAllByKodePihcLike(search, pageable);
        }

        List<KiosListResponseDTO> kiosResponseDTOS = kios.stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            KiosListResponseDTO kiosResponseDTO = new KiosListResponseDTO();
            kiosResponseDTO.setKiosId(b.getKiosId());
            kiosResponseDTO.setUserId(b.getUserId());
            kiosResponseDTO.setNama(createUserResponse.getNama());
            kiosResponseDTO.setUsername(createUserResponse.getUsername());
            kiosResponseDTO.setKodePihc(b.getKodePihc());
            kiosResponseDTO.setNamaKomoditas(b.getNamaKomoditas());
            kiosResponseDTO.setVerifikasiPasarBebas(b.getVerifikasiPasarBebas());
            return kiosResponseDTO;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(kiosResponseDTOS);
        paginationResponse.setEmpty(kios.isEmpty());
        paginationResponse.setFirst(kios.isFirst());
        paginationResponse.setLast(kios.isLast());
        paginationResponse.setNumber(kios.getNumber());
        paginationResponse.setPageable(kios.getPageable());
        paginationResponse.setSize(kios.getSize());
        paginationResponse.setSort(kios.getSort());
        paginationResponse.setTotalElements(kios.getTotalElements());
        paginationResponse.setNumberOfElements(kios.getNumberOfElements());
        paginationResponse.setTotalPages(kios.getTotalPages());
        return paginationResponse;
    }

    @Override
    public List<KiosListResponseDTO> findAllWithoutPagination(Long distributor) {
        List<Kios> kios;
        if(!Objects.isNull("distributor")) {
            if(distributor != 0) {
                kios = kiosRepository.findAllByDistributor_DistributorId(distributor);
            } else {
                kios = kiosRepository.findAll();
            }
        }else {
             kios = kiosRepository.findAll();
        }

        return kios.stream().map(b->{
            CreateUserResponse userResponse = userCreateClient.getUser(b.getUserId());
            KiosListResponseDTO dto = new KiosListResponseDTO();
            dto.setKiosId(b.getKiosId());
            dto.setUserId(b.getUserId());
            dto.setNama(userResponse.getNama());
            dto.setUsername(userResponse.getUsername());
            dto.setKodePihc(b.getKodePihc());
            dto.setNamaKomoditas(b.getNamaKomoditas());
            dto.setVerifikasiPasarBebas(b.getVerifikasiPasarBebas());
            return dto;
        }).toList();
    }

    @Override
    public KiosResponseDTO findDetail(Long kiosId) {
        Kios kios = kiosRepository.findById(kiosId).orElseThrow(()->new ResourceNotFoundException("kios.not.found"));
        CreateUserResponse createUserResponse = userCreateClient.getUser(kios.getUserId());

        List<FarmerGroupListResponseDTO> listResponseDTO = kios.getPoktan().stream().map(b->{
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(b.getUserId());
            AreaResponse area = new AreaResponse();
            if (areaResponse.size() > 0) {
                 area = areaResponse.get(0);
            }

            FarmerGroupListResponseDTO dto = new FarmerGroupListResponseDTO();
            dto.setPoktanId(b.getPoktanId());
            dto.setNamaPoktan(b.getNamaPoktan());
            dto.setKabupaten(area.getNamaKabupaten());
            dto.setKecamatan(area.getNamaKecamatan());
            dto.setDesa(b.getNamaDesa());
            dto.setStatusKonfirmasi(b.isStatusKonfirmasi());
            return dto;
        }).toList();

        List<AreaResponse> areaResponseList = userCreateClient.getAreaByUserId(kios.getUserId());
        List<AkunBankResponse> akunBankResponse = userCreateClient.getAkunBank(kios.getUserId());

        CreateUserResponse userDistributor;
        DistributorListResponseDTO distributorListResponseDTO = null;

        if(kios.getDistributor() != null) {
            distributorListResponseDTO = new DistributorListResponseDTO();
            userDistributor = userCreateClient.getUser(kios.getDistributor().getUserId());
            distributorListResponseDTO.setDistributorId(kios.getDistributor().getDistributorId());
            distributorListResponseDTO.setNama(userDistributor.getNama());
            distributorListResponseDTO.setJenisDistributor(kios.getDistributor().getJenisDistributor());
            distributorListResponseDTO.setNomorAhu(kios.getDistributor().getNomorAhu());
            distributorListResponseDTO.setStatus(kios.getDistributor().getStatus());
            distributorListResponseDTO.setWorkingAreaDistributors(kios.getDistributor().getWorkingAreaDistributors());
        }

        KiosResponseDTO kiosResponseDTO = new KiosResponseDTO();
        kiosResponseDTO.setKiosId(kios.getKiosId());
        kiosResponseDTO.setNama(createUserResponse.getNama());
        kiosResponseDTO.setFileGambar(createUserResponse.getFileGambar());
        kiosResponseDTO.setEmail(createUserResponse.getEmail());
        kiosResponseDTO.setUsername(createUserResponse.getUsername());
        kiosResponseDTO.setNomorTelepon(createUserResponse.getNomorTelepon());
        kiosResponseDTO.setKodePihc(kios.getKodePihc());
        kiosResponseDTO.setNamaKomoditas(kios.getNamaKomoditas());
        kiosResponseDTO.setVerifikasiPasarBebas(kios.getVerifikasiPasarBebas());
        kiosResponseDTO.setAlamat(kios.getAlamat());
        kiosResponseDTO.setCreatedAt(String.valueOf(kios.getCreatedAt()));
        kiosResponseDTO.setUpdatedAt(String.valueOf(kios.getUpdatedAt()));
        kiosResponseDTO.setDistributor(distributorListResponseDTO);
        kiosResponseDTO.setWilayahKerjaKios(kios.getWilayahKerjaKios());
        kiosResponseDTO.setPenyuluh(kios.getPenyuluh());
        kiosResponseDTO.setPoktan(listResponseDTO);
        kiosResponseDTO.setAreaResponse(areaResponseList);
        kiosResponseDTO.setAkunBank(akunBankResponse);
        return kiosResponseDTO;
    }

    @Override
    public KiosResponseDTO findDetailByUser(Long user) {
        Petani petani = petaniRepository.findByUserId(user);
        return this.findDetail(petani.getPoktan().getKios().getKiosId());
    }

    @Override
    public KiosResponseDTO findDetailByPoktanUser(Long poktanUserId) {
        Poktan poktan = poktanRepository.findByUserId(poktanUserId);
        return this.findDetail(poktan.getKios().getKiosId());
    }

    @Override
    public KiosResponseDTO findKiosDetailByUser(Long user) {
        Kios kios = kiosRepository.findByUserId(user);
        return this.findDetail(kios.getKiosId());
    }

    @Override
    public List<KiosListResponseDTO> findKiosListByPenyuluh(Long penyuluhUserId) {
        List<Kios> kios;

        kios = (Objects.isNull(penyuluhUserId)) ? kiosRepository.findAllByPenyuluhIsNull() : kiosRepository.findAllByPenyuluh_PenyuluhId(penyuluhUserId);

        return kios.stream().map(k->{
            CreateUserResponse response = userCreateClient.getUser(k.getUserId());
            KiosListResponseDTO kiosListResponseDTO = new KiosListResponseDTO();
            kiosListResponseDTO.setKiosId(k.getKiosId());
            kiosListResponseDTO.setUsername(response.getUsername());
            kiosListResponseDTO.setNama(response.getNama());
            kiosListResponseDTO.setKodePihc(k.getKodePihc());
            kiosListResponseDTO.setUserId(k.getUserId());
            kiosListResponseDTO.setNamaKomoditas(k.getNamaKomoditas());
            kiosListResponseDTO.setVerifikasiPasarBebas(k.getVerifikasiPasarBebas());
            return kiosListResponseDTO;
        }).toList();
    }

    @Override
    public void create(KiosCreateUpdateRequestDTO dto) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setPassword(dto.getPassword());
        createUserRequest.setFileGambar("avatar.png");
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setRole("KIOS");
        createUserRequest.setUsername(dto.getUsername());
        CreateUserResponse createUserResponse = userCreateClient.createUser(createUserRequest);

        Kios kios = new Kios();
        kios.setUserId(createUserResponse.getUserId());
        kios.setKodePihc(dto.getKodePihc());
        kios.setNamaKomoditas(dto.getNamaKomoditas());
        kios.setVerifikasiPasarBebas(dto.getVerifikasiPasarBebas() != null);
        kios.setAlamat(dto.getAlamat());
        kios.setCreatedAt(LocalDate.now());
        kios.setUpdatedAt(LocalDate.now());
        kiosRepository.save(kios);
    }

    @Override
    public void update(Long kiosId, KiosCreateUpdateRequestDTO dto) {
        Kios kios = kiosRepository.findById(kiosId).orElseThrow(()->new ResourceNotFoundException(("kios.not.found")));
        kios.setKodePihc(dto.getKodePihc());
        kios.setNamaKomoditas(dto.getNamaKomoditas());
        kios.setVerifikasiPasarBebas(dto.getVerifikasiPasarBebas());
        kios.setAlamat(dto.getAlamat());
        kios.setUpdatedAt(LocalDate.now());
        kiosRepository.save(kios);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(dto.getEmail());
        createUserRequest.setNama(dto.getNama());
        createUserRequest.setNomorTelepon(dto.getNomorTelepon());
        createUserRequest.setUsername(dto.getUsername());
        userCreateClient.updateUser(kios.getUserId(), createUserRequest);
    }

    @Override
    public void updateWorkingArea(Long kiosId, List<KiosWorkingAreaCreateUpdateDTO> dto) {
        Kios kios = kiosRepository.findById(kiosId).orElseThrow(()->new ResourceNotFoundException("kios.not.found"));

        List<WilayahKerjaKios> wilayahKerjaKios = kios.getWilayahKerjaKios();
        if(wilayahKerjaKios.size() > 0) {
            List<Long> oldArea = wilayahKerjaKios.stream().map(WilayahKerjaKios::getWilayahKerjaKiosId).toList();
            wilayahKerjaKiosRepository.deleteAllById(oldArea);
            wilayahKerjaKios.clear();
        }
        wilayahKerjaKios = dto.stream().map(b->{
            WilayahKerjaKios kerjaKios = new WilayahKerjaKios();
            kerjaKios.setKodeKabupaten(b.getKodeKabupaten());
            kerjaKios.setNamaKabupaten(b.getNamaKabupaten());
            kerjaKios.setKodeKecamatan(b.getKodeKecamatan());
            kerjaKios.setNamaKecamatan(b.getNamaKecamatan());
            kerjaKios.setKodeDesa(b.getKodeDesa());
            kerjaKios.setNamaDesa(b.getNamaDesa());
            kerjaKios.setKodeProvinsi(b.getKodeProvinsi());
            kerjaKios.setNamaProvinsi(b.getNamaProvinsi());
            return kerjaKios;
        }).collect(Collectors.toList());

        wilayahKerjaKiosRepository.saveAll(wilayahKerjaKios);
        kios.setWilayahKerjaKios(wilayahKerjaKios);
        kiosRepository.save(kios);
    }

    @Override
    public void updateDistributor(Long kiosId, Long distributorId) {
        Kios kios = kiosRepository.findById(kiosId).orElseThrow(()->new ResourceNotFoundException("kios.not.found"));
        Distributor distributor = distributorRepository.findById(distributorId).orElseThrow(() ->new ResourceNotFoundException("distributor.not.found"));

        kios.setDistributor(distributor);
        kiosRepository.save(kios);
    }

    @Override
    public void delete(Long kiosId) {
        kiosRepository.deleteById(kiosId);
    }
}
