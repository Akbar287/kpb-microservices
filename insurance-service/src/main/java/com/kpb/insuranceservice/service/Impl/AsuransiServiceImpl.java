package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.Asuransi;
import com.kpb.insuranceservice.domain.AsuransiEditLog;
import com.kpb.insuranceservice.domain.PelaksanaAsuransi;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiDetailResponseDTO;
import com.kpb.insuranceservice.dto.Asuransi.AsuransiListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.AsuransiEditLogRepository;
import com.kpb.insuranceservice.repository.AsuransiRepository;
import com.kpb.insuranceservice.repository.PelaksanaAsuransiRepository;
import com.kpb.insuranceservice.service.AsuransiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsuransiServiceImpl implements AsuransiService {

    @Autowired
    private AsuransiRepository asuransiRepository;

    @Autowired
    private AsuransiEditLogRepository asuransiEditLogRepository;

    @Autowired
    private PelaksanaAsuransiRepository pelaksanaAsuransiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "asuransiId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Asuransi> asuransi = asuransiRepository.findAllByNamaAsuransiLikeAndPublishedIsTrue(search, pageable);

        return this.paginationResponse(asuransi);
    }

    @Override
    public PaginationResponse findAllList(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "asuransiId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Asuransi> asuransi = asuransiRepository.findAllByNamaAsuransiLike(search, pageable);

        return this.paginationResponse(asuransi);
    }

    public PaginationResponse paginationResponse(Page<Asuransi> asuransi) {
        List<AsuransiListResponseDTO> findAllList = asuransi.stream().map(a->{
            AsuransiListResponseDTO dto = new AsuransiListResponseDTO();
            dto.setAsuransiId(a.getAsuransiId());
            dto.setNamaAsuransi(a.getNamaAsuransi());
            dto.setJenisAsuransi(a.getJenisAsuransi());
            dto.setBiaya(String.valueOf(a.getBiaya()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(findAllList);
        paginationResponse.setEmpty(asuransi.isEmpty());
        paginationResponse.setFirst(asuransi.isFirst());
        paginationResponse.setLast(asuransi.isLast());
        paginationResponse.setNumber(asuransi.getNumber());
        paginationResponse.setPageable(asuransi.getPageable());
        paginationResponse.setSize(asuransi.getSize());
        paginationResponse.setSort(asuransi.getSort());
        paginationResponse.setTotalElements(asuransi.getTotalElements());
        paginationResponse.setNumberOfElements(asuransi.getNumberOfElements());
        paginationResponse.setTotalPages(asuransi.getTotalPages());
        return paginationResponse;
    }

    @Override
    public AsuransiDetailResponseDTO findDetail(Long asuransiId) {
        Asuransi asuransi = asuransiRepository.findById(asuransiId).orElseThrow(()->new ResourceNotFoundException("asuransi.not.found"));

        AsuransiDetailResponseDTO asuransiDetailResponseDTO = new AsuransiDetailResponseDTO();
        asuransiDetailResponseDTO.setAsuransiId(asuransi.getAsuransiId());
        asuransiDetailResponseDTO.setNamaAsuransi(asuransi.getNamaAsuransi());
        asuransiDetailResponseDTO.setJenisAsuransi(asuransi.getJenisAsuransi());
        asuransiDetailResponseDTO.setBiaya(String.valueOf(asuransi.getBiaya()));
        asuransiDetailResponseDTO.setInformasi(asuransi.getInformasi());
        asuransiDetailResponseDTO.setPublished(asuransi.isPublished());
        asuransiDetailResponseDTO.setPelaksanaAsuransi(asuransi.getPelaksanaAsuransi());
        return asuransiDetailResponseDTO;
    }

    @Override
    public void create(AsuransiCreateUpdateRequestDTO dto) {
        PelaksanaAsuransi pelaksanaAsuransi = pelaksanaAsuransiRepository.findByUserId(dto.getPelaksanaAsuransiUserId());

        Asuransi asuransi = new Asuransi();
        asuransi.setNamaAsuransi(dto.getNamaAsuransi());
        asuransi.setJenisAsuransi(dto.getJenisAsuransi());
        asuransi.setPelaksanaAsuransi(pelaksanaAsuransi);
        asuransi.setBiaya(dto.getBiaya());
        asuransi.setInformasi(dto.getInformasi());
        asuransi.setPublished(dto.isPublished());
        asuransi.setCreatedAt(LocalDate.now());
        asuransi.setUpdatedAt(LocalDate.now());
        asuransiRepository.save(asuransi);
    }

    @Override
    public void update(Long asuransiId, AsuransiCreateUpdateRequestDTO dto) {
        List<String> strings = new ArrayList<>();
        String nama_table = "";
        Asuransi asuransi = asuransiRepository.findById(asuransiId).orElseThrow(()->new ResourceNotFoundException("asuransi.not.found"));

        if (asuransi.getNamaAsuransi().equals(dto.getNamaAsuransi())) strings.add("nama_asuransi");
        if (asuransi.getJenisAsuransi().equals(dto.getJenisAsuransi())) strings.add("jenis_asuransi");
        if (asuransi.getBiaya().equals(dto.getBiaya())) strings.add("biaya");
        if (asuransi.getInformasi().equals(dto.getInformasi())) strings.add("informasi");
        if (asuransi.isPublished() != dto.isPublished()) strings.add("published");
        if(strings.size() > 0) {
            nama_table = String.join(", ", strings);
        }

        asuransi.setNamaAsuransi(dto.getNamaAsuransi());
        asuransi.setJenisAsuransi(dto.getJenisAsuransi());
        asuransi.setBiaya(dto.getBiaya());
        asuransi.setInformasi(dto.getInformasi());
        asuransi.setPublished(dto.isPublished());
        asuransi.setUpdatedAt(LocalDate.now());
        asuransiRepository.save(asuransi);

        AsuransiEditLog asuransiEditLog = new AsuransiEditLog();
        asuransiEditLog.setAsuransi(asuransi);
        asuransiEditLog.setNamaTableEdit(nama_table);
        asuransiEditLog.setInformasi("Edited");
        asuransiEditLog.setTanggalEdit(LocalDate.now());
        asuransiEditLogRepository.save(asuransiEditLog);
    }

    @Override
    public void delete(Long asuransiId) {
        Asuransi asuransi = asuransiRepository.findById(asuransiId).orElseThrow(()->new ResourceNotFoundException("asuransi.not.found"));
        asuransi.setPublished(false);
        asuransiRepository.save(asuransi);
    }
}
