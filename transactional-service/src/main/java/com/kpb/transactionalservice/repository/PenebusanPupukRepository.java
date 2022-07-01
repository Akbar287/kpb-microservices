package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.PenebusanPupuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenebusanPupukRepository extends JpaRepository<PenebusanPupuk, Long> {
}
