package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.Asuransi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsuransiRepository extends JpaRepository<Asuransi, Long> {
    Page<Asuransi> findAllByNamaAsuransiLikeAndPublishedIsTrue(String nama, Pageable pageable);
    Page<Asuransi> findAllByNamaAsuransiLike(String nama, Pageable pageable);
}
