package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.DaftarPeserta;
import com.kpb.insuranceservice.domain.DinasKabupaten;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaDetailResponseDTO;
import com.kpb.insuranceservice.dto.DaftarPeserta.DaftarPesertaListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.DaftarPesertaRepository;
import com.kpb.insuranceservice.repository.DinasKabupatenRepository;
import com.kpb.insuranceservice.repository.PendaftaranAsuransiRepository;
import com.kpb.insuranceservice.service.DaftarPesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DaftarPesertaServiceImpl implements DaftarPesertaService {

    @Autowired
    private DaftarPesertaRepository daftarPesertaRepository;

    @Autowired
    private DinasKabupatenRepository dinasKabupatenRepository;

    @Autowired
    private PendaftaranAsuransiRepository pendaftaranAsuransiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, Integer tahun, Integer masa_tanam) {
        sort = (sort != null) ? sort : "daftarPesertaId";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<DaftarPeserta> daftarPesertas = null;

        if(ObjectUtils.isEmpty(tahun) && ObjectUtils.isEmpty(masa_tanam)) {
            daftarPesertas = daftarPesertaRepository.findAll(pageable);
        } else {
            daftarPesertas = daftarPesertaRepository.findAllByTahunAndMasaTanam(tahun, masa_tanam, pageable);
        }

        return this.paginationResponse(daftarPesertas);
    }

    @Override
    public List<DaftarPesertaListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<DaftarPeserta> daftarPesertas) {
        List<DaftarPesertaListResponseDTO> distributorListResponseDTOS = daftarPesertas.stream().map(dp -> {
            DaftarPesertaListResponseDTO dto = new DaftarPesertaListResponseDTO();
            dto.setDaftarPesertaId(dp.getDaftarPesertaId());
            dto.setTahun(dp.getTahun());
            dto.setMasaTanam(dp.getMasaTanam());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(distributorListResponseDTOS);
        paginationResponse.setEmpty(daftarPesertas.isEmpty());
        paginationResponse.setFirst(daftarPesertas.isFirst());
        paginationResponse.setLast(daftarPesertas.isLast());
        paginationResponse.setNumber(daftarPesertas.getNumber());
        paginationResponse.setPageable(daftarPesertas.getPageable());
        paginationResponse.setSize(daftarPesertas.getSize());
        paginationResponse.setSort(daftarPesertas.getSort());
        paginationResponse.setTotalElements(daftarPesertas.getTotalElements());
        paginationResponse.setNumberOfElements(daftarPesertas.getNumberOfElements());
        paginationResponse.setTotalPages(daftarPesertas.getTotalPages());
        return paginationResponse;
    }

    @Override
    public DaftarPesertaDetailResponseDTO findDetail(Long daftarPesertaId) {
        DaftarPeserta daftarPeserta = daftarPesertaRepository.findById(daftarPesertaId).orElseThrow(()->new ResourceNotFoundException("daftar.peserta.not.found"));

        DaftarPesertaDetailResponseDTO dto = new DaftarPesertaDetailResponseDTO();
        dto.setDaftarPesertaId(daftarPeserta.getDaftarPesertaId());
        dto.setTahun(daftarPeserta.getTahun());
        dto.setMasaTanam(daftarPeserta.getMasaTanam());
        dto.setNamaFile(daftarPeserta.getNamaFile());
        dto.setNamaDokumen(daftarPeserta.getNamaDokumen());
        dto.setKeterangan(daftarPeserta.getKeterangan());
        return dto;
    }

    @Override
    public void create(DaftarPesertaCreateUpdateRequestDTO dto) {
        List<PendaftaranAsuransi> pendaftaranAsuransi = pendaftaranAsuransiRepository.findAllByTahunAndMasaTanam(dto.getTahun(), dto.getMasaTanam());
        DinasKabupaten dinasKabupaten = dinasKabupatenRepository.findById(dto.getDinasKabupatenId()).orElseThrow(()->new ResourceNotFoundException("dinas.kabupaten.not.found"));

        DaftarPeserta daftarPeserta = new DaftarPeserta();
        daftarPeserta.setTahun(dto.getTahun());
        daftarPeserta.setMasaTanam(dto.getMasaTanam());
        daftarPeserta.setNamaFile(dto.getNamaFile());
        daftarPeserta.setNamaDokumen(dto.getNamaDokumen());
        daftarPeserta.setKeterangan(dto.getKeterangan());
        daftarPeserta.setPendaftaranAsuransi(pendaftaranAsuransi);
        daftarPeserta.setDinasKabupaten(dinasKabupaten);
        daftarPesertaRepository.save(daftarPeserta);
    }

    @Override
    public void update(Long daftarPesertaId, DaftarPesertaCreateUpdateRequestDTO dto) {
        DaftarPeserta daftarPeserta = daftarPesertaRepository.findById(daftarPesertaId).orElseThrow(()->new ResourceNotFoundException("daftar.peserta.not.found"));
        daftarPeserta.setTahun(dto.getTahun());
        daftarPeserta.setMasaTanam(dto.getMasaTanam());
        daftarPeserta.setNamaFile(dto.getNamaFile());
        daftarPeserta.setNamaDokumen(dto.getNamaDokumen());
        daftarPeserta.setKeterangan(dto.getKeterangan());
        daftarPesertaRepository.save(daftarPeserta);
    }

    @Override
    public void delete(Long daftarPesertaId) {
        daftarPesertaRepository.deleteById(daftarPesertaId);
    }
}
