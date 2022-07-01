package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Distributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    Page<Distributor> findAllByNomorAhuLike(String nomor, Pageable pageable);
    Page<Distributor> findAllByNomorAhuLikeAndPabrikan_PabrikanId(String nomor, Long pabrikanId, Pageable pageable);
    Distributor findByUserId(Long userId);
    Page<Distributor> findAllByNomorAhuLikeAndDistributorIdIn(String nomor, List<Long> distributor, Pageable pageable);
}
