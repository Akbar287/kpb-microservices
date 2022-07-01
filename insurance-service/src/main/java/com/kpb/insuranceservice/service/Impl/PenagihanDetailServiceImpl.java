package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.Penagihan;
import com.kpb.insuranceservice.domain.PenagihanBankDetail;
import com.kpb.insuranceservice.domain.PenagihanDetail;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailDetailResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.PenagihanDetailRepository;
import com.kpb.insuranceservice.repository.PenagihanRepository;
import com.kpb.insuranceservice.service.PenagihanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Service
public class PenagihanDetailServiceImpl implements PenagihanDetailService {

    @Autowired
    private PenagihanDetailRepository penagihanDetailRepository;

    @Autowired
    private PenagihanRepository penagihanRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort) {
        sort = (sort != null) ? sort : "penagihanDetailId";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<PenagihanDetail> penagihanDetails = penagihanDetailRepository.findAll(pageable);
        return this.paginationResponse(penagihanDetails);
    }

    @Override
    public List<PenagihanDetailListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<PenagihanDetail> penagihanDetails) {
        List<PenagihanDetailListResponseDTO> distributorListResponseDTOS = penagihanDetails.stream().map(pd-> {
            PenagihanDetailListResponseDTO dto = new PenagihanDetailListResponseDTO();
            dto.setPenagihanDetailId(pd.getPenagihanDetailId());
            dto.setJumlah(pd.getJumlah());
            dto.setTanggalBayar(String.valueOf(pd.getTanggalBayar()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(distributorListResponseDTOS);
        paginationResponse.setEmpty(penagihanDetails.isEmpty());
        paginationResponse.setFirst(penagihanDetails.isFirst());
        paginationResponse.setLast(penagihanDetails.isLast());
        paginationResponse.setNumber(penagihanDetails.getNumber());
        paginationResponse.setPageable(penagihanDetails.getPageable());
        paginationResponse.setSize(penagihanDetails.getSize());
        paginationResponse.setSort(penagihanDetails.getSort());
        paginationResponse.setTotalElements(penagihanDetails.getTotalElements());
        paginationResponse.setNumberOfElements(penagihanDetails.getNumberOfElements());
        paginationResponse.setTotalPages(penagihanDetails.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PenagihanDetailDetailResponseDTO findDetail(Long penagihanDetailId) {
        PenagihanDetail penagihanDetail = penagihanDetailRepository.findById(penagihanDetailId).orElseThrow(()->new ResourceNotFoundException("penagihan.detail.not.found"));

        PenagihanDetailDetailResponseDTO dto = new PenagihanDetailDetailResponseDTO();
        dto.setPenagihanDetailId(penagihanDetail.getPenagihanDetailId());
        dto.setTanggalBayar(String.valueOf(penagihanDetail.getTanggalBayar()));
        dto.setJumlah(penagihanDetail.getJumlah().intValue());
        dto.setMetodePembayaran(penagihanDetail.getMetodePembayaran());
        dto.setNamaFile(penagihanDetail.getNamaFile());
        dto.setNamaDokumen(penagihanDetail.getNamaDokumen());
        dto.setInformasi(penagihanDetail.getInformasi());
        dto.setCreatedAt(String.valueOf(penagihanDetail.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(penagihanDetail.getUpdatedAt()));

        dto.setPenagihan(penagihanDetail.getPenagihan());
        dto.setPenagihanBankDetail(penagihanDetail.getPenagihanBankDetail());
        return dto;
    }

    @Override
    public void create(PenagihanDetailCreateUpdateRequestDTO dto) {
        Penagihan penagihan = penagihanRepository.findById(dto.getPenagihanId()).orElseThrow(()->new ResourceNotFoundException("penagihan.not.found"));

        PenagihanDetail penagihanDetail = new PenagihanDetail();
        penagihanDetail.setNamaDokumen(dto.getNamaDokumen());
        penagihanDetail.setNamaFile(dto.getNamaFile());
        penagihanDetail.setPenagihan(penagihan);
        penagihanDetail.setJumlah(dto.getJumlah());
        penagihanDetail.setMetodePembayaran(dto.getMetodePembayaran());
        penagihanDetail.setInformasi(dto.getInformasi());
        penagihanDetail.setTanggalBayar(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getTanggalBayar()), TimeZone.getDefault().toZoneId()));
        penagihanDetail.setCreatedAt(LocalDate.now());
        penagihanDetail.setUpdatedAt(LocalDate.now());
        penagihanDetailRepository.save(penagihanDetail);
    }

    @Override
    public void update(Long penagihanDetailId, PenagihanDetailCreateUpdateRequestDTO dto) {
        PenagihanDetail penagihanDetail = penagihanDetailRepository.findById(penagihanDetailId).orElseThrow(()->new ResourceNotFoundException("penagihan.detail.not.found"));
        penagihanDetail.setNamaDokumen(dto.getNamaDokumen());
        penagihanDetail.setNamaFile(dto.getNamaFile());
        penagihanDetail.setJumlah(dto.getJumlah());
        penagihanDetail.setMetodePembayaran(dto.getMetodePembayaran());
        penagihanDetail.setInformasi(dto.getInformasi());
        penagihanDetail.setTanggalBayar(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getTanggalBayar()), TimeZone.getDefault().toZoneId()));
        penagihanDetail.setUpdatedAt(LocalDate.now());
        penagihanDetailRepository.save(penagihanDetail);
    }

    @Override
    public void delete(Long penagihanDetailId) {
        penagihanDetailRepository.deleteById(penagihanDetailId);
    }
}
