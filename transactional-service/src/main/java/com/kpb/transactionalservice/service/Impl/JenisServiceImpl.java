package com.kpb.transactionalservice.service.Impl;

import com.kpb.transactionalservice.domain.Jenis;
import com.kpb.transactionalservice.dto.Jenis.JenisRequestDTO;
import com.kpb.transactionalservice.dto.Jenis.JenisResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.JenisRepository;
import com.kpb.transactionalservice.service.JenisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class JenisServiceImpl implements JenisService {

    @Autowired
    private JenisRepository jenisRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "jenisId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Jenis> jenis = jenisRepository.findByNamaJenisLike(search, pageable);

        List<JenisResponseDTO> jenisResponseDTOS = jenis.stream().map(b->{
            JenisResponseDTO dto = new JenisResponseDTO();
            dto.setJenisId(b.getJenisId());
            dto.setNama(b.getNamaJenis());
            dto.setFileGambar(b.getFileGambar());
            dto.setDeskripsi(b.getDescription());
            dto.setCreatedAt(String.valueOf(b.getCreatedAt()));
            dto.setUpdatedAt(String.valueOf(b.getUpdatedAt()));
            dto.setProduk(b.getProduk().size());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(jenisResponseDTOS);
        paginationResponse.setEmpty(jenis.isEmpty());
        paginationResponse.setFirst(jenis.isFirst());
        paginationResponse.setLast(jenis.isLast());
        paginationResponse.setNumber(jenis.getNumber());
        paginationResponse.setPageable(jenis.getPageable());
        paginationResponse.setSize(jenis.getSize());
        paginationResponse.setSort(jenis.getSort());
        paginationResponse.setTotalElements(jenis.getTotalElements());
        paginationResponse.setNumberOfElements(jenis.getNumberOfElements());
        paginationResponse.setTotalPages(jenis.getTotalPages());
        return paginationResponse;
    }

    @Override
    public JenisResponseDTO findDetail(Long jenisId) {
        Jenis jenis = jenisRepository.findById(jenisId).orElseThrow(()->new ResourceNotFoundException("jenis.not.found"));

        JenisResponseDTO dto = new JenisResponseDTO();
        dto.setJenisId(jenis.getJenisId());
        dto.setNama(jenis.getNamaJenis());
        dto.setFileGambar(jenis.getFileGambar());
        dto.setProduk(jenis.getProduk().size());
        dto.setDeskripsi(jenis.getDescription());
        dto.setCreatedAt(String.valueOf(jenis.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(jenis.getUpdatedAt()));
        return dto;
    }

    @Override
    public void create(JenisRequestDTO dto) {
        Jenis jenis = new Jenis();
        jenis.setNamaJenis(dto.getNama());
        jenis.setFileGambar(dto.getFileGambar());
        jenis.setDescription(dto.getDeskripsi());
        jenis.setCreatedAt(LocalDate.now());
        jenis.setUpdatedAt(LocalDate.now());
        jenisRepository.save(jenis);
    }

    @Override
    public void update(Long jenisId, JenisRequestDTO dto) {
        Jenis jenis = jenisRepository.findById(jenisId).orElseThrow(()->new ResourceNotFoundException("jenis.not.found"));
        jenis.setNamaJenis(dto.getNama());
        jenis.setFileGambar(dto.getFileGambar());
        jenis.setDescription(dto.getDeskripsi());
        jenis.setCreatedAt(LocalDate.now());
        jenisRepository.save(jenis);
    }

    @Override
    public void delete(Long jenisId) {
        jenisRepository.deleteById(jenisId);
    }
}
