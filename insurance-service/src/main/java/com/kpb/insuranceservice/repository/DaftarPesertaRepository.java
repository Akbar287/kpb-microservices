package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.DaftarPeserta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaftarPesertaRepository extends JpaRepository<DaftarPeserta, Long> {
    Page<DaftarPeserta> findAllByTahunAndMasaTanam(int tahun, int masa_tanam, Pageable pageable);
}
