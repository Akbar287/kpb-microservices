package com.kpb.planningservice.service.Impl;

import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.web.Member.PetaniResponse;
import com.kpb.clientservice.web.Member.PoktanResponse;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniResponseDTO;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniResponseDTO;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanResponseDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanDetailDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanResponseDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukDetailDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukResponseDTO;
import com.kpb.planningservice.dto.PaginationResponse;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiDetailDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiResponseDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniDetailResponseDTO;
import com.kpb.planningservice.dto.RencanaUsahaTani.RencanaUsahaTaniListResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.RencanaUsahaTaniService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service @Slf4j
public class RencanaUsahaTaniServiceImpl implements RencanaUsahaTaniService {

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Autowired
    private MemberClient memberClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search, Long petani, int tahun, Long user) {
        sort = (sort != null) ? sort : "rencanaUsahaTaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        if(Objects.isNull(petani) && !Objects.isNull(user)) {
            PetaniResponse petaniResponse = memberClient.findPetaniByUserId(user);
            petani = petaniResponse.getPetaniId();
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<RencanaUsahaTani> rencanaUsahaTani;
        if(Objects.isNull(petani) && Objects.isNull(user)) {
            rencanaUsahaTani = rencanaUsahaTaniRepository.findAllByTahun(tahun, pageable);
        } else {
            rencanaUsahaTani = rencanaUsahaTaniRepository.findAllByTahunAndPetaniId(tahun, petani, pageable);
        }

        return this.paginationResponse(rencanaUsahaTani);
    }

    @Override
    public PaginationResponse findAllPoktan(int page, int size, String sort, String search, Long poktanUserId, int tahun) {
        sort = (sort != null) ? sort : "rencanaUsahaTaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        List<Long> listPetani = memberClient.findListPetaniByPoktanUserId(poktanUserId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<RencanaUsahaTani> rencanaUsahaTani;
        rencanaUsahaTani = rencanaUsahaTaniRepository.findAllByPetaniIdInAndTahun(listPetani, tahun, pageable);

        return this.paginationResponse(rencanaUsahaTani);
    }

    public  PaginationResponse paginationResponse(Page<RencanaUsahaTani> rencanaUsahaTani) {
        List<RencanaUsahaTaniListResponseDTO> rencanaUsahaTaniListResponseDTOS = rencanaUsahaTani.stream().map(b->{

            PetaniResponse petaniResponse = memberClient.findPetani(b.getPetaniId());
            RencanaUsahaTaniListResponseDTO dto = new RencanaUsahaTaniListResponseDTO();
            dto.setRencanaUsahaTaniId(b.getRencanaUsahaTaniId());
            dto.setNik(petaniResponse.getNik());
            dto.setPetani(petaniResponse.getNama());
            dto.setTahun(b.getTahun());
            dto.setLuasLahan(b.getLuasLahan());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(rencanaUsahaTaniListResponseDTOS);
        paginationResponse.setEmpty(rencanaUsahaTani.isEmpty());
        paginationResponse.setFirst(rencanaUsahaTani.isFirst());
        paginationResponse.setLast(rencanaUsahaTani.isLast());
        paginationResponse.setNumber(rencanaUsahaTani.getNumber());
        paginationResponse.setPageable(rencanaUsahaTani.getPageable());
        paginationResponse.setSize(rencanaUsahaTani.getSize());
        paginationResponse.setSort(rencanaUsahaTani.getSort());
        paginationResponse.setTotalElements(rencanaUsahaTani.getTotalElements());
        paginationResponse.setNumberOfElements(rencanaUsahaTani.getNumberOfElements());
        paginationResponse.setTotalPages(rencanaUsahaTani.getTotalPages());
        return paginationResponse;
    }

    @Override
    public RencanaUsahaTaniDetailResponseDTO findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        PetaniResponse petaniResponse = memberClient.findPetani(rencanaUsahaTani.getPetaniId());

        List<BiayaTaniResponseDTO> biayaTaniResponseDTOS;
        if(rencanaUsahaTani.getBiayaUsahaTani().size() > 0) {
            biayaTaniResponseDTOS = rencanaUsahaTani.getBiayaUsahaTani().stream().map(b -> {
                BiayaTaniResponseDTO biayaTaniResponseDTO = new BiayaTaniResponseDTO();
                biayaTaniResponseDTO.setBiayaTaniId(b.getBiayaUsahaTaniId());
                biayaTaniResponseDTO.setRencanaUsahaTaniId(b.getBiayaUsahaTaniId());
                biayaTaniResponseDTO.setHarga(b.getHarga().intValue());
                biayaTaniResponseDTO.setJenisBiayaUsahaTani(b.getJenisBiayaUsahaTani());
                biayaTaniResponseDTO.setJenis(b.getJenis());
                biayaTaniResponseDTO.setKuantitas(b.getKuantitas());
                biayaTaniResponseDTO.setSatuan(b.getSatuan());
                return biayaTaniResponseDTO;
            }).toList();
        } else {
            biayaTaniResponseDTOS = new ArrayList<>();
        }

        List<KebutuhanSaprotanResponseDTO> kebutuhanSaprotan;
        if(rencanaUsahaTani.getKebutuhanSaprotan().size() > 0) {
            kebutuhanSaprotan = rencanaUsahaTani.getKebutuhanSaprotan().stream().map(kebutuhan->{
                List<KebutuhanSaprotanDetailDTO> kebutuhanSaprotanDetailDTO = kebutuhan.getKebutuhanSaprotanDetails().stream().map(detailKebutuhan->{
                    KebutuhanSaprotanDetailDTO ks = new KebutuhanSaprotanDetailDTO();
                    ks.setKebutuhanSaprotanDetailId(detailKebutuhan.getKebutuhanSaprotanDetailId());
                    ks.setNamaSaprotan(detailKebutuhan.getNamaSaprotan());
                    ks.setJumlah(detailKebutuhan.getJumlah());
                    return ks;
                }).toList();
                KebutuhanSaprotanResponseDTO kebutuhanSaprotanResponseDTO = new KebutuhanSaprotanResponseDTO();
                kebutuhanSaprotanResponseDTO.setKebutuhanSaprotanId(kebutuhan.getKebutuhanSaprotanId());
                kebutuhanSaprotanResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
                kebutuhanSaprotanResponseDTO.setJenisSaprotan(kebutuhan.getJenisSaprotan());
                kebutuhanSaprotanResponseDTO.setMasaTanam(kebutuhan.getMasaTanam());
                kebutuhanSaprotanResponseDTO.setDetails(kebutuhanSaprotanDetailDTO);
                return kebutuhanSaprotanResponseDTO;
            }).toList();
        } else {
            kebutuhanSaprotan = new ArrayList<>();
        }

        List<KekuranganPupukResponseDTO> kekuranganPupuk;
        if(rencanaUsahaTani.getKekuranganPupuk().size() > 0) {
            kekuranganPupuk = rencanaUsahaTani.getKekuranganPupuk().stream().map(kekurangan->{
                List<KekuranganPupukDetailDTO> kekuranganPupukDetailDTOS = kekurangan.getKekuranganPupukDetails().stream().map(kpd -> {
                    KekuranganPupukDetailDTO kekuranganPupukDetailDTO = new KekuranganPupukDetailDTO();
                    kekuranganPupukDetailDTO.setKekuranganPupukDetailId(kpd.getKekuranganPupukDetaiId());
                    kekuranganPupukDetailDTO.setNamaPupuk(kpd.getNamaPupuk());
                    kekuranganPupukDetailDTO.setJumlah(kpd.getJumlah());
                    return kekuranganPupukDetailDTO;
                }).toList();

                KekuranganPupukResponseDTO kekuranganPupukResponseDTO = new KekuranganPupukResponseDTO();
                kekuranganPupukResponseDTO.setKekuranganPupukId(kekurangan.getKekuranganPupukId());
                kekuranganPupukResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
                kekuranganPupukResponseDTO.setMasaTanam(kekurangan.getMasaTanam());
                kekuranganPupukResponseDTO.setDetails(kekuranganPupukDetailDTOS);
                return kekuranganPupukResponseDTO;
            }).toList();
        } else {
            kekuranganPupuk = new ArrayList<>();
        }

        List<JenisTanamanResponseDTO> jenisTanaman;
        if(rencanaUsahaTani.getJenisTanaman().size() > 0) {
            jenisTanaman = rencanaUsahaTani.getJenisTanaman().stream().map(jenis->{
                JenisTanamanResponseDTO jenisTanamanResponseDTO = new JenisTanamanResponseDTO();
                jenisTanamanResponseDTO.setJenisTanamanId(jenis.getJenisTanamanId());
                jenisTanamanResponseDTO.setNamaTanaman(jenis.getNamaTanaman());
                jenisTanamanResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
                jenisTanamanResponseDTO.setMasaTanam(jenis.getMasaTanam());
                return  jenisTanamanResponseDTO;
            }).toList();
        } else {
            jenisTanaman = new ArrayList<>();
        }

        List<RealisasiPupukSubsidiResponseDTO> realisasiPupukSubsidi;
        if(rencanaUsahaTani.getRealisasiPupukSubsidi().size() > 0) {
            realisasiPupukSubsidi = rencanaUsahaTani.getRealisasiPupukSubsidi().stream().map(realisasi->{
                List<RealisasiPupukSubsidiDetailDTO> realisasiPupukSubsidiDetailDTOS = realisasi.getRealisasiPupukSubsidiDetails().stream().map(rps->{
                    RealisasiPupukSubsidiDetailDTO realisasiPupukSubsidiDetailDTO = new RealisasiPupukSubsidiDetailDTO();
                    realisasiPupukSubsidiDetailDTO.setRealisasiPupukSubsidiDetailId(rps.getRealisasiPupukSubsidiDetailId());
                    realisasiPupukSubsidiDetailDTO.setNamaPupuk(rps.getNamaPupuk());
                    realisasiPupukSubsidiDetailDTO.setJumlah(rps.getJumlah());
                    return realisasiPupukSubsidiDetailDTO;
                }).toList();

                RealisasiPupukSubsidiResponseDTO realisasiPupukSubsidiResponseDTO = new RealisasiPupukSubsidiResponseDTO();
                realisasiPupukSubsidiResponseDTO.setRealisasiPupukSubsidiId(realisasi.getRealisasiPupukSubsidiId());
                realisasiPupukSubsidiResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
                realisasiPupukSubsidiResponseDTO.setMasaTanam(realisasi.getMasaTanam());
                realisasiPupukSubsidiResponseDTO.setDetails(realisasiPupukSubsidiDetailDTOS);
                return realisasiPupukSubsidiResponseDTO;
            }).toList();
        } else {
            realisasiPupukSubsidi = new ArrayList<>();
        }

        List<JadwalUsahaTaniResponseDTO> jadwalUsahaTani;
        if(rencanaUsahaTani.getJadwalUsahaTani().size() > 0) {
            jadwalUsahaTani = rencanaUsahaTani.getJadwalUsahaTani().stream().map(jadwal->{
                JadwalUsahaTaniResponseDTO jadwalUsahaTaniResponseDTO = new JadwalUsahaTaniResponseDTO();
                jadwalUsahaTaniResponseDTO.setJadwalUsahaTaniId(jadwal.getJadwalUsahaTaniId());
                jadwalUsahaTaniResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
                jadwalUsahaTaniResponseDTO.setMasaTanam(jadwal.getMasaTanam());
                jadwalUsahaTaniResponseDTO.setAirTanahDrainase(String.valueOf(jadwal.getAirTanahDrainase()));
                jadwalUsahaTaniResponseDTO.setPengolahanTanah(String.valueOf(jadwal.getPengolahanTanah()));
                jadwalUsahaTaniResponseDTO.setPersemaian(String.valueOf(jadwal.getPersemaian()));
                jadwalUsahaTaniResponseDTO.setPenanaman(String.valueOf(jadwal.getPenanaman()));
                jadwalUsahaTaniResponseDTO.setPemeliharaan(String.valueOf(jadwal.getPemeliharaan()));
                jadwalUsahaTaniResponseDTO.setSemprotanHerbisida(String.valueOf(jadwal.getSemprotanHerbisida()));
                jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaPertama(String.valueOf(jadwal.getPenyemprotanFungisidaPertama()));
                jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaKedua(String.valueOf(jadwal.getPenyemprotanFungisidaKedua()));
                jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaKetiga(String.valueOf(jadwal.getPenyemprotanFungisidaKetiga()));
                jadwalUsahaTaniResponseDTO.setPemupukanDasar(String.valueOf(jadwal.getPemupukanDasar()));
                jadwalUsahaTaniResponseDTO.setPemupukanPertama(String.valueOf(jadwal.getPemupukanPertama()));
                jadwalUsahaTaniResponseDTO.setPemupukanKedua(String.valueOf(jadwal.getPemupukanKedua()));
                jadwalUsahaTaniResponseDTO.setPenyemprotanInsektisidaGandaBuah(String.valueOf(jadwal.getPenyemprotanInsektisidaGandaBuah()));
                jadwalUsahaTaniResponseDTO.setPanen(String.valueOf(jadwal.getPanen()));
                jadwalUsahaTaniResponseDTO.setGabahKeringPanen(jadwal.getPerkiraanJumlahPanen().getGabahKeringPanen());
                jadwalUsahaTaniResponseDTO.setGabahKeringGiling(jadwal.getPerkiraanJumlahPanen().getGabahKeringGiling());
                jadwalUsahaTaniResponseDTO.setBeras(jadwal.getPerkiraanJumlahPanen().getBeras());
                jadwalUsahaTaniResponseDTO.setHasilPanen(jadwal.getHasilPascaPanen().getHasilPanen());
                jadwalUsahaTaniResponseDTO.setDeskripsi(jadwal.getHasilPascaPanen().getDeskripsi());
                jadwalUsahaTaniResponseDTO.setPendapatanKotor(jadwal.getHasilPascaPanen().getPendapatanKotor().intValue());
                return jadwalUsahaTaniResponseDTO;
            }).toList();
        } else {
            jadwalUsahaTani = new ArrayList<>();
        }

        RencanaUsahaTaniDetailResponseDTO dto = new RencanaUsahaTaniDetailResponseDTO();
        dto.setRencanaUsahaTaniId(rencanaUsahaTani.getRencanaUsahaTaniId());
        dto.setTahun(rencanaUsahaTani.getTahun());
        dto.setLuasLahan(rencanaUsahaTani.getLuasLahan());
        dto.setStatus(rencanaUsahaTani.getStatus());
        dto.setTotalBiayaUsahaTani(rencanaUsahaTani.getTotalBiayaUsahaTani().intValue());
        dto.setGrandTotal(rencanaUsahaTani.getGrandTotal().intValue());
        dto.setPrediksiPendapatan(rencanaUsahaTani.getPrediksiPendapatan().intValue());
        dto.setDescription(rencanaUsahaTani.getDescription());

        dto.setPetani(petaniResponse);
        dto.setBiayaTani(biayaTaniResponseDTOS);
        dto.setKebutuhanSaprotan(kebutuhanSaprotan);
        dto.setKekuranganPupuk(kekuranganPupuk);
        dto.setJenisTanaman(jenisTanaman);
        dto.setRealisasiPupukSubsidi(realisasiPupukSubsidi);
        dto.setJadwalUsahaTani(jadwalUsahaTani);
        return dto;
    }

    @Override
    public Integer findLuasLahan(Long petaniId) {
        int tahun = LocalDate.now().getYear();
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findByTahunAndPetaniId(tahun, petaniId);
        return rencanaUsahaTani != null ? rencanaUsahaTani.getLuasLahan() : 0;
    }

    @Override
    public void create(RencanaUsahaTaniCreateUpdateRequestDTO dto) {
        PetaniResponse petaniResponse;

        try {
            if(dto.getPetaniId() == null) {
                petaniResponse = memberClient.findPetaniByUserId(dto.getUserId());
            } else {
                petaniResponse = memberClient.findPetani(dto.getPetaniId());
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("petani.not.found");
        }

        RencanaUsahaTani rencanaUsahaTani = new RencanaUsahaTani();
        rencanaUsahaTani.setPetaniId(dto.getPetaniId());
        rencanaUsahaTani.setTahun(dto.getTahun());
        rencanaUsahaTani.setLuasLahan(dto.getLuasLahan());
        rencanaUsahaTani.setStatus(dto.getStatus());
        rencanaUsahaTani.setTotalBiayaUsahaTani(BigInteger.valueOf(dto.getTotalBiayaUsahaTani()));
        rencanaUsahaTani.setGrandTotal(BigInteger.valueOf(dto.getGrandTotal()));
        rencanaUsahaTani.setPrediksiPendapatan(BigInteger.valueOf(dto.getPrediksiPendapatan()));
        rencanaUsahaTani.setDescription(dto.getDescription());
        rencanaUsahaTani.setCreatedAt(LocalDate.now());
        rencanaUsahaTani.setUpdatedAt(LocalDate.now());
        rencanaUsahaTaniRepository.save(rencanaUsahaTani);
    }

    @Override
    public void update(Long rencanaUsahaTaniId, RencanaUsahaTaniCreateUpdateRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));
        rencanaUsahaTani.setPetaniId(dto.getPetaniId());
        rencanaUsahaTani.setTahun(dto.getTahun());
        rencanaUsahaTani.setLuasLahan(dto.getLuasLahan());
        rencanaUsahaTani.setStatus(dto.getStatus());
        rencanaUsahaTani.setTotalBiayaUsahaTani(BigInteger.valueOf(dto.getTotalBiayaUsahaTani()));
        rencanaUsahaTani.setGrandTotal(BigInteger.valueOf(dto.getGrandTotal()));
        rencanaUsahaTani.setPrediksiPendapatan(BigInteger.valueOf(dto.getPrediksiPendapatan()));
        rencanaUsahaTani.setDescription(dto.getDescription());
        rencanaUsahaTani.setUpdatedAt(LocalDate.now());
        rencanaUsahaTaniRepository.save(rencanaUsahaTani);
    }

    @Override
    public void delete(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));
        rencanaUsahaTaniRepository.delete(rencanaUsahaTani);
    }
}
