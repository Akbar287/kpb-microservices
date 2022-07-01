package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.Penagihan;
import com.kpb.insuranceservice.domain.PenagihanDetail;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanDetailResponseDTO;
import com.kpb.insuranceservice.dto.Penagihan.PenagihanListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.PenagihanRepository;
import com.kpb.insuranceservice.repository.PendaftaranAsuransiRepository;
import com.kpb.insuranceservice.service.PenagihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class PenagihanServiceImpl implements PenagihanService {

    @Autowired
    private PenagihanRepository penagihanRepository;

    @Autowired
    private PendaftaranAsuransiRepository pendaftaranAsuransiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "penagihanId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Penagihan> penagihan = null;

        if(search.equals("%")) {
            penagihan = penagihanRepository.findAll(pageable);
        } else {
            List<PendaftaranAsuransi> listPendaftaranAsuransiId = pendaftaranAsuransiRepository.findAllByNomorAsuransiLike(search);
            penagihan = penagihanRepository.findAllByPendaftaranAsuransiIn(listPendaftaranAsuransiId.stream().map(PendaftaranAsuransi::getPendaftaranAsuransiId).toList(), pageable);
        }

        return this.paginationResponse(penagihan);
    }

    @Override
    public List<PenagihanListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<Penagihan> penagihan) {
        List<PenagihanListResponseDTO> penagihanListResponseDTOS = penagihan.stream().map(p-> {
            PenagihanListResponseDTO dto = new PenagihanListResponseDTO();
            dto.setPenagihanId(p.getPenagihanId());
            dto.setBatasWaktu(String.valueOf(p.getBatasWaktu()));
            dto.setStatus(p.getStatus());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(penagihanListResponseDTOS);
        paginationResponse.setEmpty(penagihan.isEmpty());
        paginationResponse.setFirst(penagihan.isFirst());
        paginationResponse.setLast(penagihan.isLast());
        paginationResponse.setNumber(penagihan.getNumber());
        paginationResponse.setPageable(penagihan.getPageable());
        paginationResponse.setSize(penagihan.getSize());
        paginationResponse.setSort(penagihan.getSort());
        paginationResponse.setTotalElements(penagihan.getTotalElements());
        paginationResponse.setNumberOfElements(penagihan.getNumberOfElements());
        paginationResponse.setTotalPages(penagihan.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PenagihanDetailResponseDTO findDetail(Long penagihanId) {
        Penagihan penagihan = penagihanRepository.findById(penagihanId).orElseThrow(()->new ResourceNotFoundException("penagihan.not.found"));
        PenagihanDetailResponseDTO dto = new PenagihanDetailResponseDTO();
        dto.setPenagihanId(penagihan.getPenagihanId());
        dto.setBatasWaktu(String.valueOf(penagihan.getBatasWaktu()));
        dto.setMinimalPembayaran(penagihan.getMinimalPembayaran());
        dto.setSaldo(penagihan.getSaldo());
        dto.setStatus(penagihan.getStatus());
        dto.setCreatedAt(String.valueOf(penagihan.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(penagihan.getUpdatedAt()));
        dto.setPenagihanDetail(penagihan.getPenagihanDetail());
        dto.setPendaftaranAsuransi(penagihan.getPendaftaranAsuransi());
        return dto;
    }

    @Override
    public void create(PenagihanCreateUpdateRequestDTO dto) {
        PendaftaranAsuransi pendaftaranAsuransi = pendaftaranAsuransiRepository.findById(dto.getPendaftaranAsuransiId()).orElseThrow(()->new ResourceNotFoundException("pendaftaran.asuransi.not.found"));
        Penagihan penagihan = new Penagihan();
        penagihan.setPendaftaranAsuransi(pendaftaranAsuransi);
        penagihan.setBatasWaktu(dto.getBatasWaktu());
        penagihan.setMinimalPembayaran(dto.getMinimalPembayaran());
        penagihan.setSaldo(dto.getSaldo());
        penagihan.setStatus(dto.getStatus());
        penagihan.setCreatedAt(LocalDate.now());
        penagihan.setUpdatedAt(LocalDate.now());
        penagihanRepository.save(penagihan);
    }

    @Override
    public void update(Long penagihanId, PenagihanCreateUpdateRequestDTO dto) {
        Penagihan penagihan = penagihanRepository.findById(penagihanId).orElseThrow(()->new ResourceNotFoundException("penagihan.not.found"));
        penagihan.setBatasWaktu(dto.getBatasWaktu());
        penagihan.setMinimalPembayaran(dto.getMinimalPembayaran());
        penagihan.setSaldo(dto.getSaldo());
        penagihan.setStatus(dto.getStatus());
        penagihan.setUpdatedAt(LocalDate.now());
        penagihanRepository.save(penagihan);
    }

    @Override
    public void delete(Long penagihanId) {
        penagihanRepository.deleteById(penagihanId);
    }
}
