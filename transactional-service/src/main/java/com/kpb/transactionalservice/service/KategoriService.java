package com.kpb.transactionalservice.service;

import com.kpb.transactionalservice.dto.Kategori.KategoriRequestDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;

public interface KategoriService {
    public PaginationResponse findAll(int page, int size, String sort, String search);
    public KategoriResponseDTO findDetail(Long kategoriId);
    public void create(KategoriRequestDTO dto);
    public void update(Long kategoriId, KategoriRequestDTO dto);
    public void delete(Long kategoriId);
}
