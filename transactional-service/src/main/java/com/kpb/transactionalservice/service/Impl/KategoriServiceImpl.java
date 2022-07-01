package com.kpb.transactionalservice.service.Impl;

import com.kpb.transactionalservice.domain.Kategori;
import com.kpb.transactionalservice.dto.Kategori.KategoriRequestDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.KategoriRepository;
import com.kpb.transactionalservice.service.KategoriService;
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
public class KategoriServiceImpl implements KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "kategoriId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Kategori> kategori = kategoriRepository.findByNamaKategoriLike(search, pageable);

        List<KategoriResponseDTO> kategoriResponseDTOList = kategori.stream().map(b->{
            KategoriResponseDTO dto = new KategoriResponseDTO();
            dto.setKategoriId(b.getKategoriId());
            dto.setNamaKategori(b.getNamaKategori());
            dto.setFileGambar(b.getFileGambar());
            dto.setDeskripsi(b.getDeskripsi());
            dto.setProduk(b.getProduk().size());
            dto.setCreatedAt(String.valueOf(b.getCreatedAt()));
            dto.setUpdatedAt(String.valueOf(b.getUpdatedAt()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(kategoriResponseDTOList);
        paginationResponse.setEmpty(kategori.isEmpty());
        paginationResponse.setFirst(kategori.isFirst());
        paginationResponse.setLast(kategori.isLast());
        paginationResponse.setNumber(kategori.getNumber());
        paginationResponse.setPageable(kategori.getPageable());
        paginationResponse.setSize(kategori.getSize());
        paginationResponse.setSort(kategori.getSort());
        paginationResponse.setTotalElements(kategori.getTotalElements());
        paginationResponse.setNumberOfElements(kategori.getNumberOfElements());
        paginationResponse.setTotalPages(kategori.getTotalPages());
        return paginationResponse;
    }

    @Override
    public KategoriResponseDTO findDetail(Long kategoriId) {
        Kategori kategori = kategoriRepository.findById(kategoriId).orElseThrow(()->new ResourceNotFoundException("kategori.not.found"));

        KategoriResponseDTO dto = new KategoriResponseDTO();
        dto.setKategoriId(kategori.getKategoriId());
        dto.setNamaKategori(kategori.getNamaKategori());
        dto.setFileGambar(kategori.getFileGambar());
        dto.setDeskripsi(kategori.getDeskripsi());
        dto.setCreatedAt(String.valueOf(kategori.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(kategori.getUpdatedAt()));
        return dto;
    }

    @Override
    public void create(KategoriRequestDTO dto) {
        Kategori kategori = new Kategori();
        kategori.setNamaKategori(dto.getNamaKategori());
        kategori.setFileGambar(dto.getFileGambar());
        kategori.setDeskripsi(dto.getDeskripsi());
        kategori.setCreatedAt(LocalDate.now());
        kategori.setUpdatedAt(LocalDate.now());
        kategoriRepository.save(kategori);
    }

    @Override
    public void update(Long kategoriId, KategoriRequestDTO dto) {
        Kategori kategori = kategoriRepository.findById(kategoriId).orElseThrow(()->new ResourceNotFoundException("kategori.not.found"));
        kategori.setNamaKategori(dto.getNamaKategori());
        kategori.setFileGambar(dto.getFileGambar());
        kategori.setDeskripsi(dto.getDeskripsi());
        kategori.setUpdatedAt(LocalDate.now());
        kategoriRepository.save(kategori);
    }

    @Override
    public void delete(Long kategoriId) {
        kategoriRepository.deleteById(kategoriId);
    }
}
