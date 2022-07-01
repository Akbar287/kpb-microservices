package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.Klaim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlaimRepository extends JpaRepository<Klaim, Long> {
    Page<Klaim> findAllByKodeLike(String kode, Pageable pageable);
}
