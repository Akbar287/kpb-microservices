package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.PenebusanPupukStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenebusanPupukStatusRepository extends JpaRepository<PenebusanPupukStatus, Long> {
}
