package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.PelaksanaAsuransi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PelaksanaAsuransiRepository extends JpaRepository<PelaksanaAsuransi, Long> {
    Page<PelaksanaAsuransi> findAllByNamaKetuaLike(String namaKetua, Pageable pageable);
    PelaksanaAsuransi findByUserId(Long userId);
}
