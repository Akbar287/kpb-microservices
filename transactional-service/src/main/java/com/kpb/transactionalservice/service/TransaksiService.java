package com.kpb.transactionalservice.service;

import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiCreateRequestDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiDetailResponseDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiUpdateRequestDTO;

public interface TransaksiService {
    public PaginationResponse findAll(int page, int size, String sort, String search, Long userId, String jenis, Long kiosUserId);
    public PaginationResponse findAllToPoktan(int page, int size, String sort, String search, Long poktanUserId, String jenis);
    public PaginationResponse findAllToAllRole(int page, int size, String sort, String search, Long userId, String jenis, String role);
    public TransaksiDetailResponseDTO findDetail(Long transaksiId);
    public void create(TransaksiCreateRequestDTO dto);
    public void update(Long transaksiId, TransaksiUpdateRequestDTO dto);
    public void delete(Long transaksiId);
}
