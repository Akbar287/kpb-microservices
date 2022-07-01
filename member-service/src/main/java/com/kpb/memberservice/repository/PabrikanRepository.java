package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Pabrikan;
import com.kpb.memberservice.domain.Poktan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabrikanRepository extends JpaRepository<Pabrikan, Long> {
    Page<Pabrikan> findAllByJenisPabrikLike(String jenis, Pageable pageable);

    Pabrikan findByUserId(Long userId);
}
