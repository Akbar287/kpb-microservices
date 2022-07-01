package com.kpb.transactionalservice.service;

import com.kpb.clientservice.web.Transaksi.StokProdukResponse;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.Produk.ProdukListResponseDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukRequestDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;

import java.util.List;

public interface ProdukService {
    public PaginationResponse findAll(int page, int size, String sort, String search,String kategori,String jenis, Long kios, Long userId);
    public PaginationResponse findAllPoktan(int page, int size, String sort, String search,String kategori,String jenis, Long poktanUserId);
    public ProdukResponseDTO findDetail(Long produkId);
    public List<ProdukListResponseDTO> findAllProdukWithPenebusan();
    public StokProdukResponse findStokProdukByNamaAndTahunAndBulan(String nama, int tahun, String bulan);
    public void create(ProdukRequestDTO dto);
    public void update(Long produkId, ProdukRequestDTO dto);
    public void delete(Long produkId);
}
