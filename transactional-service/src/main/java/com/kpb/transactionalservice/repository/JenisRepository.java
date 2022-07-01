package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Jenis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisRepository extends JpaRepository<Jenis, Long> {
    Page<Jenis> findByNamaJenisLike(String nama, Pageable pageable);
    Jenis findByNamaJenis(String jenis);
}
