package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.Asuransi;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.domain.UsahaTani;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.PendaftaranAsuransi.PendaftaranAsuransiListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.AsuransiRepository;
import com.kpb.insuranceservice.repository.PendaftaranAsuransiRepository;
import com.kpb.insuranceservice.repository.UsahaTaniRepository;
import com.kpb.insuranceservice.service.PendaftaranAsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Service
public class PendaftaranAsuransiServiceImpl implements PendaftaranAsuransiService {

    @Autowired
    private PendaftaranAsuransiRepository pendaftaranAsuransiRepository;

    @Autowired
    private AsuransiRepository asuransiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "pendaftaranAsuransiId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<PendaftaranAsuransi> pendaftaranAsuransi = pendaftaranAsuransiRepository.findAllByNomorAsuransiLike(search, pageable);

        return this.paginationResponse(pendaftaranAsuransi);
    }

    @Override
    public List<PendaftaranAsuransiListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<PendaftaranAsuransi> pendaftaranAsuransi) {
        List<PendaftaranAsuransiListResponseDTO> pendaftaranAsuransiListResponseDTOS = pendaftaranAsuransi.stream().map(pa-> {
            PendaftaranAsuransiListResponseDTO dto = new PendaftaranAsuransiListResponseDTO();
            dto.setPendaftaranAsuransiId(pa.getPendaftaranAsuransiId());
            dto.setNomorAsuransi(pa.getNomorAsuransi());
            dto.setMetodePembayaran(pa.getMetodePembayaran());
            dto.setTotalPembayaran(pa.getTotalPembayaran());
            dto.setWaktuEfektif(String.valueOf(pa.getWaktuEfektif()));
            dto.setWaktuBerakhir(String.valueOf(pa.getWaktuBerakhir()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(pendaftaranAsuransiListResponseDTOS);
        paginationResponse.setEmpty(pendaftaranAsuransi.isEmpty());
        paginationResponse.setFirst(pendaftaranAsuransi.isFirst());
        paginationResponse.setLast(pendaftaranAsuransi.isLast());
        paginationResponse.setNumber(pendaftaranAsuransi.getNumber());
        paginationResponse.setPageable(pendaftaranAsuransi.getPageable());
        paginationResponse.setSize(pendaftaranAsuransi.getSize());
        paginationResponse.setSort(pendaftaranAsuransi.getSort());
        paginationResponse.setTotalElements(pendaftaranAsuransi.getTotalElements());
        paginationResponse.setNumberOfElements(pendaftaranAsuransi.getNumberOfElements());
        paginationResponse.setTotalPages(pendaftaranAsuransi.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PendaftaranAsuransiDetailResponseDTO findDetail(Long pendaftaranAsuransiId) {
        PendaftaranAsuransi pendaftaranAsuransi = pendaftaranAsuransiRepository.findById(pendaftaranAsuransiId).orElseThrow(()->new ResourceNotFoundException("pendaftaran.asuransi.not.found"));

        PendaftaranAsuransiDetailResponseDTO dto = new PendaftaranAsuransiDetailResponseDTO();
        dto.setPendaftaranAsuransiId(pendaftaranAsuransi.getPendaftaranAsuransiId());
        dto.setNomorAsuransi(pendaftaranAsuransi.getNomorAsuransi());
        dto.setTahun(pendaftaranAsuransi.getTahun());
        dto.setMasaTanam(pendaftaranAsuransi.getMasaTanam());
        dto.setWaktuEfektif(String.valueOf(pendaftaranAsuransi.getWaktuEfektif()));
        dto.setWaktuBerakhir(String.valueOf(pendaftaranAsuransi.getWaktuBerakhir()));
        dto.setMetodePembayaran(pendaftaranAsuransi.getMetodePembayaran());
        dto.setTotalPembayaran(pendaftaranAsuransi.getTotalPembayaran().intValue());
        dto.setNamaFile(pendaftaranAsuransi.getNamaFile());
        dto.setNamaDokumen(pendaftaranAsuransi.getNamaDokumen());
        dto.setAktif(pendaftaranAsuransi.isAktif());
        dto.setInformasi(pendaftaranAsuransi.getInformasi());
        dto.setAsuransi(pendaftaranAsuransi.getAsuransi());
        return dto;
    }

    @Override
    public void create(PendaftaranAsuransiCreateUpdateRequestDTO dto) {
        Asuransi asuransi = asuransiRepository.findById(dto.getAsuransiId()).orElseThrow(()->new ResourceNotFoundException("asuransi.not.found"));

        PendaftaranAsuransi pendaftaranAsuransi = new PendaftaranAsuransi();
        pendaftaranAsuransi.setAsuransi(asuransi);
        pendaftaranAsuransi.setNomorAsuransi(dto.getNomorAsuransi());
        pendaftaranAsuransi.setTahun(dto.getTahun());
        pendaftaranAsuransi.setMasaTanam(dto.getMasaTanam());
        pendaftaranAsuransi.setWaktuEfektif(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getWaktuEfektif()), TimeZone.getDefault().toZoneId()));
        pendaftaranAsuransi.setWaktuBerakhir(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getWaktuBerakhir()), TimeZone.getDefault().toZoneId()));
        pendaftaranAsuransi.setMetodePembayaran(dto.getMetodePembayaran());
        pendaftaranAsuransi.setTotalPembayaran(BigInteger.valueOf(dto.getTotalPembayaran()));
        pendaftaranAsuransi.setNamaFile(dto.getNamaFile());
        pendaftaranAsuransi.setNamaDokumen(dto.getNamaDokumen());
        pendaftaranAsuransi.setAktif(dto.isAktif());
        pendaftaranAsuransi.setInformasi(dto.getInformasi());
        pendaftaranAsuransi.setCreatedAt(LocalDate.now());
        pendaftaranAsuransi.setUpdatedAt(LocalDate.now());
        pendaftaranAsuransiRepository.save(pendaftaranAsuransi);
    }

    @Override
    public void update(Long pendaftaranAsuransiId, PendaftaranAsuransiCreateUpdateRequestDTO dto) {
        PendaftaranAsuransi pendaftaranAsuransi = pendaftaranAsuransiRepository.findById(pendaftaranAsuransiId).orElseThrow(()->new ResourceNotFoundException("pendaftaran.asuransi.not.found"));
        pendaftaranAsuransi.setNomorAsuransi(dto.getNomorAsuransi());
        pendaftaranAsuransi.setTahun(dto.getTahun());
        pendaftaranAsuransi.setMasaTanam(dto.getMasaTanam());
        pendaftaranAsuransi.setWaktuEfektif(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getWaktuEfektif()), TimeZone.getDefault().toZoneId()));
        pendaftaranAsuransi.setWaktuBerakhir(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getWaktuBerakhir()), TimeZone.getDefault().toZoneId()));
        pendaftaranAsuransi.setMetodePembayaran(dto.getMetodePembayaran());
        pendaftaranAsuransi.setTotalPembayaran(BigInteger.valueOf(dto.getTotalPembayaran()));
        pendaftaranAsuransi.setNamaFile(dto.getNamaFile());
        pendaftaranAsuransi.setNamaDokumen(dto.getNamaDokumen());
        pendaftaranAsuransi.setAktif(dto.isAktif());
        pendaftaranAsuransi.setInformasi(dto.getInformasi());
        pendaftaranAsuransi.setUpdatedAt(LocalDate.now());
        pendaftaranAsuransiRepository.save(pendaftaranAsuransi);
    }

    @Override
    public void delete(Long pendaftaranAsuransiId) {
        pendaftaranAsuransiRepository.deleteById(pendaftaranAsuransiId);
    }
}
