package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.DinasPopt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinasPoptRepository extends JpaRepository<DinasPopt, Long> {
    Page<DinasPopt> findAllByNamaKetuaLike(String nama, Pageable pageable);
}
