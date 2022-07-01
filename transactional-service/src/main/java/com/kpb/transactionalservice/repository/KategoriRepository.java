package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Kategori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriRepository extends JpaRepository<Kategori, Long> {
    Kategori findByNamaKategori(String nama);
    Page<Kategori> findByNamaKategoriLike(String nama, Pageable pageable);
}
