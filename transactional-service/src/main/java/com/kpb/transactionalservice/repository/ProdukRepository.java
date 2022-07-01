package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Produk;
import com.kpb.transactionalservice.domain.ProdukDistributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, Long> {
    Page<Produk> findAllByNamaProdukLike(String nama, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndKategori_NamaKategoriAndJenis_NamaJenis(String nama, String kategori, String jenis, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndJenis_NamaJenis(String nama,  String jenis, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndKategori_NamaKategori(String nama, String kategori, Pageable pageable);
    Produk findByNamaProduk(String nama);
    @Query("Select p from Produk p where p.isPenebusan = true")
    List<Produk> findAllByPenebusanIsTrue();
    Page<Produk> findAllByNamaProdukLikeAndProdukDistributor_DistributorId(String search, Long distributor, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategori(String search, Long distributor, String kategori, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndJenis_NamaJenis(String search, Long distributor, String jenis, Pageable pageable);
    Page<Produk> findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategoriAndJenis_NamaJenis(String search, Long distributor, String kategori, String jenis, Pageable pageable);

}
