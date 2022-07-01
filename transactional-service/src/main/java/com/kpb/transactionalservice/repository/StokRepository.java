package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Stok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StokRepository extends JpaRepository<Stok, Long> {
    Stok findByDistributorIdAndBulanAndTahunAndKiosIdAndProduk_ProdukId(Long distributorId, String bulan, int tahun, Long kiosId, Long produkId);

    Stok findAllByDistributorIdAndKiosIdAndProduk_ProdukIdAndBulanAndTahun(Long distributor, Long kios, Long produk, String bulan, int tahun);
}
