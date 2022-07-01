package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.PenagihanBankDetail;
import com.kpb.insuranceservice.domain.PenagihanDetail;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailListResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanBankDetail.PenagihanBankDetailResponseDTO;
import com.kpb.insuranceservice.dto.PenagihanDetail.PenagihanDetailDetailResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.PenagihanBankDetailRepository;
import com.kpb.insuranceservice.repository.PenagihanDetailRepository;
import com.kpb.insuranceservice.service.PenagihanBankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Service
public class PenagihanBankDetailServiceImpl implements PenagihanBankDetailService {

    @Autowired
    private PenagihanBankDetailRepository penagihanBankDetailRepository;

    @Autowired
    private PenagihanDetailRepository penagihanDetailRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "penagihanDetailBankId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<PenagihanBankDetail> penagihanBankDetails = penagihanBankDetailRepository.findAllByNomorRekeningLike(search, pageable);

        return this.paginationResponse(penagihanBankDetails);
    }

    @Override
    public List<PenagihanBankDetailListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<PenagihanBankDetail> penagihanBankDetails) {
        List<PenagihanBankDetailListResponseDTO> penagihanBankDetailListResponseDTOS = penagihanBankDetails.stream().map(pbd-> {
            PenagihanBankDetailListResponseDTO dto = new PenagihanBankDetailListResponseDTO();
            dto.setPenagihanBankDetailId(pbd.getPenagihanDetailBankId());
            dto.setNamaBank(pbd.getNamaBank());
            dto.setNomorKartu(pbd.getNomorKartu());
            dto.setNomorRekening(pbd.getNomorRekening());
            dto.setPembayarNamaPertama(pbd.getPembayarNamaPertama());
            dto.setPembayarNamaTerakhir(pbd.getPembayarNamaTerakhir());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(penagihanBankDetailListResponseDTOS);
        paginationResponse.setEmpty(penagihanBankDetails.isEmpty());
        paginationResponse.setFirst(penagihanBankDetails.isFirst());
        paginationResponse.setLast(penagihanBankDetails.isLast());
        paginationResponse.setNumber(penagihanBankDetails.getNumber());
        paginationResponse.setPageable(penagihanBankDetails.getPageable());
        paginationResponse.setSize(penagihanBankDetails.getSize());
        paginationResponse.setSort(penagihanBankDetails.getSort());
        paginationResponse.setTotalElements(penagihanBankDetails.getTotalElements());
        paginationResponse.setNumberOfElements(penagihanBankDetails.getNumberOfElements());
        paginationResponse.setTotalPages(penagihanBankDetails.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PenagihanBankDetailResponseDTO findDetail(Long penagihanBankDetailId) {
        PenagihanBankDetail penagihanBankDetail = penagihanBankDetailRepository.findById(penagihanBankDetailId).orElseThrow(()->new ResourceNotFoundException("penagihan.bank.detail.not.found"));

        PenagihanBankDetailResponseDTO dto = new PenagihanBankDetailResponseDTO();
        dto.setPenagihanBankDetailId(penagihanBankDetail.getPenagihanDetailBankId());
        dto.setPembayarNamaPertama(penagihanBankDetail.getPembayarNamaPertama());
        dto.setPembayarNamaTerakhir(penagihanBankDetail.getPembayarNamaTerakhir());
        dto.setNomorKartu(penagihanBankDetail.getNomorKartu());
        dto.setNamaBank(penagihanBankDetail.getNamaBank());
        dto.setNomorRekening(penagihanBankDetail.getNomorRekening());
        dto.setJenisKartu(penagihanBankDetail.getJenisKartu());
        dto.setTanggalExpireKartu(String.valueOf(penagihanBankDetail.getTanggalExpireKartu()));
        dto.setPenagihanDetail(penagihanBankDetail.getPenagihanDetail());
        return dto;
    }

    @Override
    public void create(PenagihanBankDetailCreateUpdateRequestDTO dto) {
        PenagihanDetail penagihanDetail = penagihanDetailRepository.findById(dto.getPenagihanDetailId()).orElseThrow(()->new ResourceNotFoundException("penagihan.detail.not.found"));

        PenagihanBankDetail penagihanBankDetail = new PenagihanBankDetail();
        penagihanBankDetail.setNamaBank(dto.getNamaBank());
        penagihanBankDetail.setJenisKartu(dto.getJenisKartu());
        penagihanBankDetail.setNomorKartu(dto.getNomorKartu());
        penagihanBankDetail.setNomorRekening(dto.getNomorRekening());
        penagihanBankDetail.setPembayarNamaPertama(dto.getPembayarNamaPertama());
        penagihanBankDetail.setPembayarNamaTerakhir(dto.getPembayarNamaTerakhir());
        penagihanBankDetail.setTanggalExpireKartu(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getTanggalExpireKartu()), TimeZone.getDefault().toZoneId()));
        penagihanBankDetail.setPenagihanDetail(penagihanDetail);
        penagihanBankDetailRepository.save(penagihanBankDetail);
    }

    @Override
    public void update(Long penagihanBankDetailId, PenagihanBankDetailCreateUpdateRequestDTO dto) {
        PenagihanBankDetail penagihanBankDetail = penagihanBankDetailRepository.findById(penagihanBankDetailId).orElseThrow(()->new ResourceNotFoundException("penagihan.bank.detail.not.found"));
        penagihanBankDetail.setNamaBank(dto.getNamaBank());
        penagihanBankDetail.setJenisKartu(dto.getJenisKartu());
        penagihanBankDetail.setNomorKartu(dto.getNomorKartu());
        penagihanBankDetail.setNomorRekening(dto.getNomorRekening());
        penagihanBankDetail.setPembayarNamaPertama(dto.getPembayarNamaPertama());
        penagihanBankDetail.setPembayarNamaTerakhir(dto.getPembayarNamaTerakhir());
        penagihanBankDetail.setTanggalExpireKartu(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getTanggalExpireKartu()), TimeZone.getDefault().toZoneId()));
        penagihanBankDetailRepository.save(penagihanBankDetail);
    }

    @Override
    public void delete(Long penagihanBankDetailId) {
        penagihanBankDetailRepository.deleteById(penagihanBankDetailId);
    }
}
