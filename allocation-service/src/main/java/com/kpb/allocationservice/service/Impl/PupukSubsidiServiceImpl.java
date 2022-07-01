package com.kpb.allocationservice.service.Impl;

import com.kpb.allocationservice.domain.AlokasiPupukSubsidi;
import com.kpb.allocationservice.domain.PupukSubsidi;
import com.kpb.allocationservice.domain.PupukSubsidiDetail;
import com.kpb.allocationservice.domain.PupukSubsidiStatus;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiResponseDTO;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.NamaPupukDanJumlahHelper;
import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.allocationservice.dto.PupukSubsidi.*;
import com.kpb.allocationservice.exception.ResourceNotFoundException;
import com.kpb.allocationservice.repository.PupukSubsidiDetailRepository;
import com.kpb.allocationservice.repository.PupukSubsidiRepository;
import com.kpb.allocationservice.repository.PupukSubsidiStatusRepository;
import com.kpb.allocationservice.service.PupukSubsidiService;
import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.feign.RutClient;
import com.kpb.clientservice.feign.TransaksiClient;
import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.clientservice.web.Member.KomoditasSubsektorResponse;
import com.kpb.clientservice.web.Member.PetaniResponse;
import com.kpb.clientservice.web.Member.PoktanResponse;
import com.kpb.clientservice.web.Transaksi.StokProdukResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service @Slf4j
public class PupukSubsidiServiceImpl implements PupukSubsidiService {

    @Autowired
    private PupukSubsidiRepository pupukSubsidiRepository;

    @Autowired
    private PupukSubsidiStatusRepository pupukSubsidiStatusRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private PupukSubsidiDetailRepository pupukSubsidiDetailRepository;

    @Autowired
    private TransaksiClient transaksiClient;

    @Autowired
    private RutClient rutClient;

    @Override
    public PaginationResponse findAllList(Long petani, int page, int size, Long user) {
        if(Objects.isNull(petani) && !Objects.isNull(user)) {
            PetaniResponse petaniResponse = memberClient.findPetaniByUserId(user);
            petani = petaniResponse.getPetaniId();
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("tahun").descending());
        Page<PupukSubsidi> pupukSubsidi = pupukSubsidiRepository.findByPetaniId(petani, pageable);
        return this.paginationResponse(pupukSubsidi);
    }

    @Override
    public PaginationResponse findAllListPoktan(int page, int size, Long user, Integer tahun) {
        List<Long> listPetani = memberClient.findListPetaniByPoktanUserId(user);
        Pageable pageable = PageRequest.of(page, size, Sort.by("tahun").descending());
        Page<PupukSubsidi> pupukSubsidi = null;

        if(Objects.isNull(tahun)) {
            pupukSubsidi = pupukSubsidiRepository.findAllByPetaniIdIn(listPetani, pageable);
        } else {
            pupukSubsidi = pupukSubsidiRepository.findAllByTahunAndPetaniIdIn(tahun, listPetani, pageable);
        }
        return this.paginationResponse(pupukSubsidi);
    }

    @Override
    public PaginationResponse findAllListbyAllRole(int page, int size, String role, Long user, int tahun) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("tahun").descending());
        Page<PupukSubsidi> pupukSubsidi = null;

        if(role.equals("tph")) {
            List<Long> listPetani = memberClient.findListPetaniByPoktanUserId(user);
            pupukSubsidi = pupukSubsidiRepository.findAllByTahunAndPetaniIdIn(tahun, listPetani, pageable);
        } else {
            pupukSubsidi = pupukSubsidiRepository.findAll(pageable);
        }
        return this.paginationResponse(pupukSubsidi);
    }

    @Override
    public PaginationResponse findAllListBinatani(int page, int size, Long user) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("tahun").descending());
        Page<PupukSubsidi> pupukSubsidi = pupukSubsidiRepository.findAll(pageable);
        return this.paginationResponse(pupukSubsidi);
    }

    @Override
    public PaginationResponse findAllListPenyuluh(int page, int size, Long user) {
        List<Long> listKios = memberClient.findListKiosByPenyuluhUserId(user);

        Pageable pageable = PageRequest.of(page, size, Sort.by("tahun").descending());
        Page<PupukSubsidi> pupukSubsidi = pupukSubsidiRepository.findAllByKiosIdIn(listKios, pageable);
        return this.paginationResponse(pupukSubsidi);
    }

    public PaginationResponse paginationResponse (Page<PupukSubsidi> pupukSubsidi) {
        List<PupukSubsidiListResponseDTO> pupukSubsidiListResponseDTOS = pupukSubsidi.stream().map(b-> {
            PetaniResponse petaniResponse = memberClient.findPetani(b.getPetaniId());
            PupukSubsidiListResponseDTO dto = new PupukSubsidiListResponseDTO();
            dto.setPupukSubsidiId(b.getPupukSubsidiId());
            dto.setNama(petaniResponse.getNama());
            dto.setNik(petaniResponse.getNik());
            dto.setMasaTanam(b.getMasaTanam());
            dto.setTahun(b.getTahun());
            dto.setDigunakan(b.isDigunakan());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(pupukSubsidiListResponseDTOS);
        paginationResponse.setEmpty(pupukSubsidi.isEmpty());
        paginationResponse.setFirst(pupukSubsidi.isFirst());
        paginationResponse.setLast(pupukSubsidi.isLast());
        paginationResponse.setNumber(pupukSubsidi.getNumber());
        paginationResponse.setPageable(pupukSubsidi.getPageable());
        paginationResponse.setSize(pupukSubsidi.getSize());
        paginationResponse.setSort(pupukSubsidi.getSort());
        paginationResponse.setTotalElements(pupukSubsidi.getTotalElements());
        paginationResponse.setNumberOfElements(pupukSubsidi.getNumberOfElements());
        paginationResponse.setTotalPages(pupukSubsidi.getTotalPages());
        return paginationResponse;
    }
    @Override
    public SisaPupukSubsidiResponseDTO findSisaPupuk(Long petani, int tahun, int masa_tanam,String pupuk) {
        CreateUserResponse userResponse = null;
        PetaniResponse petaniResponse = null;
        try {
            petaniResponse = memberClient.findPetani(petani);
            userResponse = userCreateClient.getUser(petaniResponse.getUserId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("petani.or.user.not.found");
        }

            tahun = (tahun != 0) ? tahun :  LocalDateTime.now().getYear();
            masa_tanam = (masa_tanam != 0) ? masa_tanam : this.getMasaTanambyMonth(LocalDate.now().getMonthValue());

            PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findByTahunAndMasaTanamAndPetaniId(tahun, masa_tanam, petani);

            List<NamaPupukDanJumlahHelper> namaPupukDanJumlahHelpers = new java.util.ArrayList<>(pupukSubsidi.getPupukSubsidiDetail().stream().map(detail -> {
                NamaPupukDanJumlahHelper namaPupukDanJumlahHelper = new NamaPupukDanJumlahHelper();
                AtomicInteger jml = new AtomicInteger();
                pupukSubsidi.getAlokasiPupukSubsidi().forEach(alokasi -> {
                    if (alokasi.getNamaPupuk().equals(detail.getNamaPupuk())) {
                        jml.addAndGet(alokasi.getJumlah());
                    }
                });

                namaPupukDanJumlahHelper.setNamaPupuk(detail.getNamaPupuk());
                namaPupukDanJumlahHelper.setJumlah(detail.getJumlah() - jml.get());
                return namaPupukDanJumlahHelper;
            }).toList());

            if (pupuk != null) {
                NamaPupukDanJumlahHelper pupukDanJumlahHelper = namaPupukDanJumlahHelpers.stream().filter(x-> x.getNamaPupuk().equals(pupuk)).findFirst().orElse(null);
                namaPupukDanJumlahHelpers.clear();
                namaPupukDanJumlahHelpers.add(pupukDanJumlahHelper);
            }

            SisaPupukSubsidiResponseDTO responseDTO = new SisaPupukSubsidiResponseDTO();
            responseDTO.setPupukSubsidiId(pupukSubsidi.getPupukSubsidiId());
            responseDTO.setNama(userResponse.getNama());
            responseDTO.setUsername(userResponse.getUsername());
            responseDTO.setMasaTanam(masa_tanam);
            responseDTO.setTahun(tahun);
            responseDTO.setPupuk(namaPupukDanJumlahHelpers);
            return responseDTO;
    }

    @Override
    public InformasiAlokasiPupukSubsidiPoktanResponse alokasiPupukOPDTPH(Long poktanUserId, int tahun) {
        List<Long> listPetani = memberClient.findListPetaniByPoktanUserId(poktanUserId);
        PoktanResponse poktan = memberClient.findPoktanByUserId(poktanUserId);
        KiosResponse kios = memberClient.findKiosByPoktanUser(poktanUserId);

        List<InformasiSubPetaniAlokasiResponse> alokasiResponses = new ArrayList<>();
        listPetani.forEach(a->{
            Integer luasLahan = rutClient.findLuasLahan(a);
            PetaniResponse petaniResponse = memberClient.findPetani(a);
            for (int i = 1; i <= 3; i++) {
                InformasiSubPetaniAlokasiResponse info = new InformasiSubPetaniAlokasiResponse();
                KomoditasSubsektorResponse komoditasSubsektorResponse = memberClient.findKomoditasSubsektor(a, i);
                List<PupukSubsidi> pupukSubsidi = pupukSubsidiRepository.findAllByPetaniIdAndTahunAndMasaTanam(a, tahun, i);
                List<InformasiSubPetaniPupukResponse> r = new ArrayList<>();

                if(pupukSubsidi != null) {
                   pupukSubsidi.forEach(b->{
                        b.getPupukSubsidiDetail().forEach(c->{

                            AtomicInteger jml = new AtomicInteger();
                            jml.set(0);
                            if(b.getAlokasiPupukSubsidi().size() > 0) {
                                b.getAlokasiPupukSubsidi().forEach(d->{
                                    if (d.getNamaPupuk().equals(c.getNamaPupuk())) {
                                        jml.addAndGet(d.getJumlah());
                                    }
                                });
                            }

                            InformasiSubPetaniPupukResponse informasiSubPetaniPupukResponse = new InformasiSubPetaniPupukResponse();
                            informasiSubPetaniPupukResponse.setNamaPupuk(c.getNamaPupuk());
                            informasiSubPetaniPupukResponse.setJumlahAwal(c.getJumlah());
                            informasiSubPetaniPupukResponse.setDigunakan(jml.get());
                            informasiSubPetaniPupukResponse.setSisa(c.getJumlah() - jml.get());
                            r.add(informasiSubPetaniPupukResponse);
                        });

                        info.setPupukSubsidiId(b.getPupukSubsidiId());
                    });
                }

                info.setMasaTanam(i);
                info.setPupuk(r);
                info.setKomoditas(komoditasSubsektorResponse.getNamaKomoditas() != null ? komoditasSubsektorResponse.getNamaKomoditas() : "");
                info.setSubsektor(komoditasSubsektorResponse.getSubsektor() != null ? komoditasSubsektorResponse.getSubsektor() : "");
                info.setLuasLahan(luasLahan != null ? luasLahan : 0);
                info.setPetaniId(petaniResponse.getPetaniId());
                info.setNik(petaniResponse.getNik());
                info.setNama(petaniResponse.getNama());
                info.setTahun(tahun);
                alokasiResponses.add(info);
            }
        });

        InformasiAlokasiPupukSubsidiPoktanResponse response = new InformasiAlokasiPupukSubsidiPoktanResponse();
        response.setPoktanId(poktan.getPoktanId());
        response.setNamaPoktan(poktan.getNamaPoktan());
        response.setPetani(alokasiResponses);
        response.setKios(kios);
        return response;
    }

    @Override
    public PupukSubsidiDetailResponseDTO findAlokasiDetailByPetaniAndYear(Long petani, Long userId, int tahun, int masa_tanam) {
        if(petani == null) {
            if(userId == null) {
                throw new ResourceNotFoundException("user.petani.not.found");
            } else {
                PetaniResponse petaniResponse = memberClient.findPetaniByUserId(userId);
                petani = petaniResponse.getPetaniId();
            }
        }
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findByTahunAndMasaTanamAndPetaniId(tahun, masa_tanam, petani);

        if(pupukSubsidi == null) {
            PupukSubsidiDetailResponseDTO pupukSubsidiDetailResponseDTO = new PupukSubsidiDetailResponseDTO();
            return pupukSubsidiDetailResponseDTO;
        }
        return this.findDetail(pupukSubsidi.getPupukSubsidiId());
    }

    @Override
    public List<PupukSubsidiDetailResponseDTO> findAlokasiDetailByPoktanAndYear(Long userId, int tahun, int masa_tanam) {
        List<Long> listPetani = memberClient.findListPetaniByPoktanUserId(userId);
        List<PupukSubsidi> pupukSubsidi = pupukSubsidiRepository.findAllByTahunAndMasaTanamAndPetaniIdIn(tahun, masa_tanam, listPetani);
        return pupukSubsidi.stream().map(b-> this.findDetail(b.getPupukSubsidiId())).toList();
    }

    public int getMasaTanambyMonth(int month) {
        if(month == 1 || month == 2 || month == 3 || month == 4) {
            return 1;
        } else if (month == 5 || month == 6 || month == 7 || month == 8) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public PupukSubsidiDetailResponseDTO findDetail(Long pupukSubsidiId) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findById(pupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("pupuksubsidi.not.found"));
        PetaniResponse petaniResponse = memberClient.findPetani(pupukSubsidi.getPetaniId());
        CreateUserResponse userResponse = userCreateClient.getUser(petaniResponse.getUserId());
        PupukSubsidiDetailResponseDTO dto = new PupukSubsidiDetailResponseDTO();
        dto.setPupukSubsidiId(pupukSubsidi.getPupukSubsidiId());
        dto.setNama(userResponse.getNama());
        dto.setPetaniId(petaniResponse.getPetaniId());
        dto.setTahun(pupukSubsidi.getTahun());
        dto.setMasaTanam(pupukSubsidi.getMasaTanam());
        dto.setKomoditas(pupukSubsidi.getKomoditas());
        dto.setSubsektor(pupukSubsidi.getSubsektor());
        dto.setDigunakan(pupukSubsidi.isDigunakan());
        dto.setLuasLahan(pupukSubsidi.getLuasLahan());
        dto.setPesan(pupukSubsidi.getPesan());

        int tahun = LocalDate.now().getYear();
        String bulan = parseMonth(LocalDate.now().getMonthValue());

        List<AlokasiResponseDTO> alokasiResponseDTOS = pupukSubsidi.getPupukSubsidiDetail().stream().map(b->{
            AlokasiPupukSubsidi a = b.getPupukSubsidi().getAlokasiPupukSubsidi().stream().filter(x->x.getNamaPupuk().equals(b.getNamaPupuk())).findFirst().orElse(null);
            int digunakan = (a != null) ? a.getJumlah() : 0;

            StokProdukResponse stokProdukResponse = transaksiClient.getStokProdukbyNamaProdukAndTahunAndBulan(b.getNamaPupuk(), tahun, bulan);

            AlokasiResponseDTO alokasiResponseDTO = new AlokasiResponseDTO();
            alokasiResponseDTO.setProdukId(stokProdukResponse.getProdukId());
            alokasiResponseDTO.setNamaPupuk(b.getNamaPupuk());
            alokasiResponseDTO.setJumlah(b.getJumlah());
            alokasiResponseDTO.setJenis(stokProdukResponse.getJenis());
            alokasiResponseDTO.setKategori(stokProdukResponse.getKategori());

            alokasiResponseDTO.setHarga(stokProdukResponse == null ? 0 : stokProdukResponse.getHarga());
            alokasiResponseDTO.setHarga_subsidi(stokProdukResponse == null ? 0 : stokProdukResponse.getHarga_subsidi());
            alokasiResponseDTO.setHarga_kios_subsidi(stokProdukResponse == null ? 0 : stokProdukResponse.getHarga_kios_subsidi());

            alokasiResponseDTO.setStokAwal(stokProdukResponse == null ? 0 : stokProdukResponse.getStokAwal());
            alokasiResponseDTO.setStokAkhir(stokProdukResponse == null ? 0 : stokProdukResponse.getStokAkhir());
            alokasiResponseDTO.setStokPenyaluran(stokProdukResponse == null ? 0 : stokProdukResponse.getStokPenyaluran());
            alokasiResponseDTO.setStokSubsidi(stokProdukResponse == null ? 0 : stokProdukResponse.getStokSubsidi());
            alokasiResponseDTO.setDigunakan(digunakan);
            alokasiResponseDTO.setSisa(b.getJumlah() - digunakan);
            return alokasiResponseDTO;
        }).toList();

        KiosResponse kiosResponse = memberClient.findKios(pupukSubsidi.getKiosId());

        dto.setKiosResponse(kiosResponse);
        dto.setAlokasi(alokasiResponseDTOS);
        dto.setPupukSubsidiStatus(pupukSubsidi.getPupukSubsidiStatus());
        return dto;
    }

    @Override
    public void create(PupukSubsidiCreateUpdateRequestDTO dto) {
        try {
            memberClient.findPetani(dto.getPetaniId());
            memberClient.findKios(dto.getKiosId());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("petani.or.kios.not.found");
        }

        PupukSubsidi pupukSubsidi = new PupukSubsidi();
        pupukSubsidi.setPetaniId(dto.getPetaniId());
        pupukSubsidi.setKiosId(dto.getKiosId());
        pupukSubsidi.setTahun(dto.getTahun());
        pupukSubsidi.setMasaTanam(dto.getMasaTanam());
        pupukSubsidi.setKomoditas(dto.getKomoditas());
        pupukSubsidi.setSubsektor(dto.getSubsektor());
        pupukSubsidi.setDigunakan(dto.isDigunakan());
        pupukSubsidi.setLuasLahan(dto.getLuasLahan());
        pupukSubsidi.setPesan(dto.getPesan());
        pupukSubsidi.setCreatedAt(LocalDate.now());
        pupukSubsidi.setUpdatedAt(LocalDate.now());
        pupukSubsidiRepository.save(pupukSubsidi);

        List<PupukSubsidiDetail> pupukSubsidiDetails = dto.getPupukSubsidiDetail().stream().map(b->{
            PupukSubsidiDetail pupukSubsidiDetail = new PupukSubsidiDetail();
            pupukSubsidiDetail.setPupukSubsidi(pupukSubsidi);
            pupukSubsidiDetail.setNamaPupuk(b.getNamaPupuk());
            pupukSubsidiDetail.setJumlah(b.getJumlah());
            return pupukSubsidiDetail;
        }).toList();
        pupukSubsidiDetailRepository.saveAll(pupukSubsidiDetails);

        PupukSubsidiStatus pupukSubsidiStatus = new PupukSubsidiStatus();
        pupukSubsidiStatus.setLabel("Alokasi Pupuk Bersubsidi");
        pupukSubsidiStatus.setPesan("Alokasi Pupuk Bersubsidi Ditambah");
        pupukSubsidiStatus.setPupukSubsidi(pupukSubsidi);
        pupukSubsidiStatus.setCreatedAt(LocalDate.now());
        pupukSubsidiStatusRepository.save(pupukSubsidiStatus);
    }

    @Override
    public void update(Long pupukSubsidiId, PupukSubsidiCreateUpdateRequestDTO dto) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findById(pupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("pupuksubsidi.not.found"));
        pupukSubsidi.setPetaniId(dto.getPetaniId());
        pupukSubsidi.setKiosId(dto.getKiosId());
        pupukSubsidi.setTahun(dto.getTahun());
        pupukSubsidi.setMasaTanam(dto.getMasaTanam());
        pupukSubsidi.setKomoditas(dto.getKomoditas());
        pupukSubsidi.setSubsektor(dto.getSubsektor());
        pupukSubsidi.setDigunakan(dto.isDigunakan());
        pupukSubsidi.setLuasLahan(dto.getLuasLahan());
        pupukSubsidi.setPesan(dto.getPesan());
        pupukSubsidi.setUpdatedAt(LocalDate.now());
        pupukSubsidiRepository.save(pupukSubsidi);

        if(pupukSubsidi.getPupukSubsidiDetail().size() > 0) pupukSubsidiDetailRepository.deleteAll(pupukSubsidi.getPupukSubsidiDetail());


        List<PupukSubsidiDetail> pupukSubsidiDetails = dto.getPupukSubsidiDetail().stream().map(b->{
            PupukSubsidiDetail pupukSubsidiDetail = new PupukSubsidiDetail();
            pupukSubsidiDetail.setPupukSubsidi(pupukSubsidi);
            pupukSubsidiDetail.setNamaPupuk(b.getNamaPupuk());
            pupukSubsidiDetail.setJumlah(b.getJumlah());
            return pupukSubsidiDetail;
        }).toList();
        pupukSubsidiDetailRepository.saveAll(pupukSubsidiDetails);

        PupukSubsidiStatus pupukSubsidiStatus = new PupukSubsidiStatus();
        pupukSubsidiStatus.setLabel("Alokasi Pupuk Bersubsidi");
        pupukSubsidiStatus.setPesan("Alokasi Pupuk Bersubsidi Diupdate");
        pupukSubsidiStatus.setPupukSubsidi(pupukSubsidi);
        pupukSubsidiStatus.setCreatedAt(LocalDate.now());
        pupukSubsidiStatusRepository.save(pupukSubsidiStatus);
    }

    @Override
    public void delete(Long pupukSubsidiId) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findById(pupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("pupuksubsidi.not.found"));
        pupukSubsidiDetailRepository.deleteAll(pupukSubsidi.getPupukSubsidiDetail());
        pupukSubsidiStatusRepository.deleteAll(pupukSubsidi.getPupukSubsidiStatus());
        pupukSubsidiRepository.deleteById(pupukSubsidiId);
    }

    String parseMonth(int month) {
        if (month == 1) return "Januari";
        else if (month == 2) return "Februari";
        else if (month == 3) return "Maret";
        else if (month == 4) return "April";
        else if (month == 5) return "Mei";
        else if (month == 6) return "Juni";
        else if (month == 7) return "Juli";
        else if (month == 8) return "Agustus";
        else if (month == 9) return "September";
        else if (month == 10) return "Oktober";
        else if (month == 11) return "November";
        else if (month == 12) return "Desember";
        else return null;
    }
}
