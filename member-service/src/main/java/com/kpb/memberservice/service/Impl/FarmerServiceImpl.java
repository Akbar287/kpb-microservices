package com.kpb.memberservice.service.Impl;

import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.AkunBankResponse;
import com.kpb.clientservice.web.AreaResponse;
import com.kpb.clientservice.web.CreateUserRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.clientservice.web.Member.KomoditasSubsektorResponse;
import com.kpb.memberservice.domain.*;
import com.kpb.memberservice.dto.PaginationResponse;
import com.kpb.memberservice.dto.farmer.*;
import com.kpb.memberservice.exception.ResourceNotFoundException;
import com.kpb.memberservice.repository.*;
import com.kpb.memberservice.service.FarmerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service @Slf4j
public class FarmerServiceImpl implements FarmerService {
    @Autowired
    private PetaniRepository petaniRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private LuasLahanRepository luasLahanRepository;

    @Autowired
    private KepemilikanRepository kepemilikanRepository;

    @Autowired
    private KeluargaPetaniRepository keluargaPetaniRepository;

    @Autowired
    private TanggunganPetaniRepository tanggunganPetaniRepository;

    @Autowired
    private TanggunganLainRepository tanggunganLainRepository;

    @Autowired
    private AsetPetaniRepository asetPetaniRepository;

    @Autowired
    private MasaTanamPetaniRepository masaTanamPetaniRepository;

    @Autowired
    private PenyuluhRepository penyuluhRepository;

    @Override
    public PaginationResponse findAllFarmer(int page, int size, String sort, String search, Long kios) {
        sort = (sort != null) ? sort : "petaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Petani> petani;
        if(!Objects.isNull(kios)) {
            petani = petaniRepository.findAllByNikLikeAndPoktan_Kios_UserId(search, kios, pageable);
        } else {
             petani = petaniRepository.findAllByNikLike(search, pageable);
        }
        return this.paginationResponse(petani);
    }

    @Override
    public List<FarmerListResponseDTO> findAllFarmerWithoutPagination() {
        List<Petani> petani = petaniRepository.findAll();
        return petani.stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(createUserResponse.getUserId());
            AreaResponse area = new AreaResponse();
            if(areaResponse.size() > 0) area = areaResponse.get(0);
            FarmerListResponseDTO dto = new FarmerListResponseDTO();
            dto.setPetaniId(b.getPetaniId());
            dto.setNama(createUserResponse.getNama());
            dto.setStatusPetani(b.getStatusPetani());
            dto.setNamaDesa(area.getNamaDesa());
            dto.setNik(b.getNik());
            dto.setNomorTelepon(createUserResponse.getNomorTelepon());
            dto.setIbuKandung(b.getIbuKandung());
            return dto;
        }).toList();
    }

    @Override
    public FarmerDetailResponseDTO findFarmerDetail(Long farmerId) {
        Petani petani = petaniRepository.findById(farmerId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));
        CreateUserResponse createUserResponse = userCreateClient.getUser(petani.getUserId());
        List<AkunBankResponse> akunBankResponse = userCreateClient.getAkunBank(petani.getUserId());
        List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(petani.getUserId());

        List<LuasLahan> luasLahan = petani.getLuasLahan();
        FarmerDetailResponseDTO dto = new FarmerDetailResponseDTO();
        dto.setPetaniId(petani.getPetaniId());
        dto.setNik(petani.getNik());
        dto.setUserId(petani.getUserId());
        dto.setNama(createUserResponse.getNama());
        dto.setAlamat(petani.getAlamat());
        dto.setStatusPernikahan(petani.getStatusPernikahan());
        dto.setNomorTelepon(createUserResponse.getNomorTelepon());
        dto.setEmail(createUserResponse.getEmail());
        dto.setUsername(createUserResponse.getUsername());
        dto.setFileGambar(createUserResponse.getFileGambar());
        dto.setIbuKandung(petani.getIbuKandung());
        dto.setStatusPetani(petani.getStatusPetani());
        dto.setTempatLahir(petani.getTempatLahir());
        dto.setTanggalLahir(String.valueOf(petani.getTanggalLahir()));
        dto.setAgama(petani.getAgama());
        dto.setPendidikan(petani.getPendidikan());
        dto.setNomorKK(petani.getNomorKK());
        dto.setJenisKelamin(petani.getJenisKelamin());
        dto.setJenisPetani(petani.getJenisPetani());
        dto.setArea(areaResponse);
        dto.setKepemilikan(petani.getKepemilikan());
        dto.setLuasLahan(luasLahan);
        dto.setKeluargaPetani(petani.getKeluargaPetani());
        dto.setAsetPetani(petani.getAsetPetani());
        dto.setAkunBank(akunBankResponse);
        return dto;
    }

    @Override
    public Boolean checkNik(String nik) {
        Petani petani = petaniRepository.findByNik(nik);
        return petani == null;
    }

    @Override
    public KomoditasSubsektorResponse findKomoditasAndSubsektor(Long petaniId) {
        Petani petani = petaniRepository.findById(petaniId).orElse(null);

        String komoditas = null;
        String subsektor = null;
        if(petani != null){
            if(petani.getAsetPetani().size() > 0) {
                if(petani.getAsetPetani().get(0).getMasaTanamPetani().size() > 0) {
                    komoditas = petani.getAsetPetani().get(0).getMasaTanamPetani().get(0).getNamaKomoditas();
                    subsektor = petani.getAsetPetani().get(0).getMasaTanamPetani().get(0).getSubsektor();
                }
            }
        }

        KomoditasSubsektorResponse komoditasSubsektorResponse = new KomoditasSubsektorResponse();
        komoditasSubsektorResponse.setSubsektor(komoditas != null ? komoditas : "");
        komoditasSubsektorResponse.setNamaKomoditas(subsektor !=null ? subsektor : "");
        return komoditasSubsektorResponse;
    }

    @Override
    public List<Long> searchNik(String nik) {
        List<Petani> list = petaniRepository.findAllByNikLike(nik);
        return list.stream().map(Petani::getPetaniId).toList();
    }

    @Override
    public FarmerDetailResponseDTO farmerDetailResponseDTOByUser(Long userId) {
        Petani petani = petaniRepository.findByUserId(userId);
        return this.findFarmerDetail(petani.getPetaniId());
    }

    @Override
    public PaginationResponse farmerDetailResponseDTOByPoktan(int page, int size, String sort, String search, Long userId) {
        sort = (sort != null) ? sort : "petaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Petani> petani = petaniRepository.findAllByNikLikeAndPoktan_UserId(search, userId, pageable);
        return this.paginationResponse(petani);
    }

    @Override
    public PaginationResponse farmerDetailResponseDTOByAllRole(int page, int size, String sort, String search, String role, Long userId) {
        sort = (sort != null) ? sort : "petaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Petani> petani = null;
        if(role.equals("penyuluh")) {
            Penyuluh penyuluh = penyuluhRepository.findByUserId(userId);
            List<Long> listPoktan = new ArrayList<>();
            penyuluh.getKiosList().forEach(kios->{
                List<Long> list_ = kios.getPoktan().stream().map(Poktan::getPoktanId).toList();
                listPoktan.addAll(list_);
            });
            petani = petaniRepository.findAllByNikLikeAndPoktan_PoktanIdIn(search, listPoktan, pageable);
        } else {
            petani = petaniRepository.findAllByNikLikeAndPoktan_UserId(search, userId, pageable);
        }
        return this.paginationResponse(petani);
    }

    public PaginationResponse paginationResponse(Page<Petani> petani) {
        List<FarmerListResponseDTO> farmerListResponseDTOS = petani.stream().map(b->{
            CreateUserResponse createUserResponse = userCreateClient.getUser(b.getUserId());
            List<AreaResponse> areaResponse = userCreateClient.getAreaByUserId(createUserResponse.getUserId());
            AreaResponse area = new AreaResponse();
            if(areaResponse.size() > 0) area = areaResponse.get(0);
            FarmerListResponseDTO dto = new FarmerListResponseDTO();
            dto.setPetaniId(b.getPetaniId());
            dto.setNama(createUserResponse.getNama());
            dto.setStatusPetani(b.getStatusPetani());
            dto.setNamaDesa(area.getNamaDesa());
            dto.setNik(b.getNik());
            dto.setNomorTelepon(createUserResponse.getNomorTelepon());
            dto.setIbuKandung(b.getIbuKandung());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(farmerListResponseDTOS);
        paginationResponse.setEmpty(petani.isEmpty());
        paginationResponse.setFirst(petani.isFirst());
        paginationResponse.setLast(petani.isLast());
        paginationResponse.setNumber(petani.getNumber());
        paginationResponse.setPageable(petani.getPageable());
        paginationResponse.setSize(petani.getSize());
        paginationResponse.setSort(petani.getSort());
        paginationResponse.setTotalElements(petani.getTotalElements());
        paginationResponse.setNumberOfElements(petani.getNumberOfElements());
        paginationResponse.setTotalPages(petani.getTotalPages());
        return paginationResponse;
    }
    @Override
    public FarmerDetailResponseDTO updateFarmer(Long farmerId, FarmerUpdateRequestDTO farmerUpdateRequestDTO) {
        Petani petani = petaniRepository.findById(farmerId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));
        petani.setStatusPetani(farmerUpdateRequestDTO.getStatusPetani());
        petani.setAlamat(farmerUpdateRequestDTO.getAlamat());
        petani.setTempatLahir(farmerUpdateRequestDTO.getTempatLahir());
        petani.setTanggalLahir(LocalDate.parse(farmerUpdateRequestDTO.getTanggalLahir()));
        petani.setNomorKK(farmerUpdateRequestDTO.getNomorKk());
        petani.setJenisKelamin(farmerUpdateRequestDTO.getJenisKelamin());
        petani.setAgama(farmerUpdateRequestDTO.getAgama());
        petani.setNik(farmerUpdateRequestDTO.getNik());
        petani.setPendidikan(farmerUpdateRequestDTO.getPendidikan());
        petani.setStatusPernikahan(farmerUpdateRequestDTO.getStatusPernikahan());
        petani.setIbuKandung(farmerUpdateRequestDTO.getIbuKandung());
        petani.setJenisPetani(farmerUpdateRequestDTO.getJenisPetani());
        petaniRepository.save(petani);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername(farmerUpdateRequestDTO.getUsername());
        createUserRequest.setEmail(farmerUpdateRequestDTO.getEmail());
        createUserRequest.setNama(farmerUpdateRequestDTO.getNama());
        createUserRequest.setNomorTelepon(farmerUpdateRequestDTO.getNomorTelepon());

        userCreateClient.updateUser(petani.getUserId(), createUserRequest);

        return this.findFarmerDetail(farmerId);
    }

    @Override
    public void updateAssetFarmer(Long farmerId, List<FarmerAssetCreateUpdateRequestDTO> farmerAssetCreateUpdateRequestDTO) {
        Petani petani = petaniRepository.findById(farmerId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));

        if(petani.getAsetPetani() != null) {
            petani.getAsetPetani().forEach(b-> {
                List<Long> oldMt = b.getMasaTanamPetani().stream().map(MasaTanamPetani::getMasaTanamPetaniId).toList();
                masaTanamPetaniRepository.deleteAllById(oldMt);
            });
            asetPetaniRepository.deleteAll(petani.getAsetPetani());
            petani.setAsetPetani(null);
        }

        farmerAssetCreateUpdateRequestDTO.forEach(aset->{
            AsetPetani asetPetani = new AsetPetani();
            asetPetani.setPetani(petani);
            asetPetani.setJenisAset(aset.getJenisAset());
            asetPetani.setTotalAset(aset.getTotalAset());
            asetPetani.setNamaAset(aset.getNamaAset());
            asetPetaniRepository.save(asetPetani);

            List<MasaTanamPetani> masaTanam = aset.getMasaTanamPetani().stream().map(mt->{
                MasaTanamPetani masaTanamPetani = new MasaTanamPetani();
                masaTanamPetani.setJumlah(mt.getJumlah());
                masaTanamPetani.setSubsektor(mt.getSubsektor());
                masaTanamPetani.setMasaTanam(mt.getMasaTanam());
                masaTanamPetani.setNamaKomoditas(mt.getNamaKomoditas());
                masaTanamPetani.setAsetPetani(asetPetani);
                return masaTanamPetani;
            }).toList();
            masaTanamPetaniRepository.saveAll(masaTanam);
        });
    }

    @Override
    public void updateFamilyFarmer(Long farmerId, FarmerFamilyCreateUpdateRequestDTO farmerFamilyCreateUpdateRequestDTO) {
        Petani petani = petaniRepository.findById(farmerId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));

        KeluargaPetani keluargaPetani;
        if(petani.getKeluargaPetani() == null) {
            keluargaPetani = new KeluargaPetani();
        } else {
             keluargaPetani = petani.getKeluargaPetani();
             tanggunganLainRepository.deleteAll(keluargaPetani.getTanggunganLainList());
             tanggunganPetaniRepository.deleteAll(keluargaPetani.getTanggunganPetaniList());
             keluargaPetani.setTanggunganPetaniList(null);
             keluargaPetani.setTanggunganLainList(null);
        }
        keluargaPetani.setKepalaKeluarga(farmerFamilyCreateUpdateRequestDTO.getKepalaKeluarga());
        keluargaPetani.setPetani(petani);
        keluargaPetaniRepository.save(keluargaPetani);


        List<TanggunganLain> tanggunganLains = farmerFamilyCreateUpdateRequestDTO.getTanggunganLain().stream().map((dependent)->{
            TanggunganLain tanggunganLain = new TanggunganLain();
            tanggunganLain.setNamaLengkap(dependent.getNamaLengkap());
            tanggunganLain.setTempatLahir(dependent.getTempatLahir());
            tanggunganLain.setTanggalLahir(LocalDate.ofInstant(Instant.ofEpochSecond(dependent.getTanggalLahir()), TimeZone.getDefault().toZoneId()));
            tanggunganLain.setHubunganKepalaKeluarga(dependent.getHubunganKepalaKeluarga());
            tanggunganLain.setKeluargaPetani(keluargaPetani);
            return tanggunganLain;
        }).collect(Collectors.toList());

        tanggunganLainRepository.saveAll(tanggunganLains);

        List<TanggunganPetani> familyResponsibilities = farmerFamilyCreateUpdateRequestDTO.getTanggunganPetani().stream().map((family)->{
            TanggunganPetani tanggunganPetani = new TanggunganPetani();
            tanggunganPetani.setNamaAnak(family.getNamaAnak());
            tanggunganPetani.setNomorAnak(family.getNomorAnak());
            tanggunganPetani.setTempatLahir(family.getTempatLahir());
            tanggunganPetani.setTanggalLahir(LocalDate.ofInstant(Instant.ofEpochSecond(family.getTanggalLahir()), TimeZone.getDefault().toZoneId()));
            tanggunganPetani.setPendidikan(family.getPendidikan());
            tanggunganPetani.setKeluargaPetani(keluargaPetani);
            return tanggunganPetani;
        }).collect(Collectors.toList());

        tanggunganPetaniRepository.saveAll(familyResponsibilities);
    }

    @Override
    public void updateOwnershipFarmer(Long farmerId, FarmerOwnershipCreateUpdateRequestDTO farmerOwnershipCreateUpdateRequestDTO) {
        Petani petani = petaniRepository.findById(farmerId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));
        Kepemilikan kepemilikan;
        if(petani.getKepemilikan() == null) {
            kepemilikan = new Kepemilikan();
        } else {
            kepemilikan = petani.getKepemilikan();
        }
        kepemilikan.setFasilitasListrik(farmerOwnershipCreateUpdateRequestDTO.getFasilitasListrik());
        kepemilikan.setStatusRumah(farmerOwnershipCreateUpdateRequestDTO.getStatusRumah());
        kepemilikan.setStatusTanah(farmerOwnershipCreateUpdateRequestDTO.getStatusTanah());
        kepemilikan.setKendaraan(farmerOwnershipCreateUpdateRequestDTO.getKendaraan());
        kepemilikan.setPetani(petani);
        kepemilikanRepository.save(kepemilikan);
    }

    @Override
    public void updateSurfaceAreaFarmer(Long petaniId, FarmerSurfaceAreaCreateUpdateRequestDTO farmerSurfaceAreaCreateUpdateRequestDTO) {
        Petani petani = petaniRepository.findById(petaniId).orElseThrow(()->new ResourceNotFoundException("farmer.not.found"));
        LuasLahan surface;
        if(farmerSurfaceAreaCreateUpdateRequestDTO.getLuasLahanId() != 0) {
            surface = luasLahanRepository.findById(farmerSurfaceAreaCreateUpdateRequestDTO.getLuasLahanId()).orElseThrow(()->new ResourceNotFoundException("surface.not.found"));

            if(farmerSurfaceAreaCreateUpdateRequestDTO.getJenisMasaTanam() == 0) {
                luasLahanRepository.delete(surface);
                return;
            }
        }
        surface = new LuasLahan();
        surface.setJenisMasaTanam(farmerSurfaceAreaCreateUpdateRequestDTO.getJenisMasaTanam());
        surface.setLuasLahan(farmerSurfaceAreaCreateUpdateRequestDTO.getLuasLahan());
        surface.setPetani(petani);
        luasLahanRepository.save(surface);
    }

    @Override
    public void deleteFarmer(Long farmerId) {
        petaniRepository.deleteById(farmerId);
    }
}
