package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.TransaksiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiDetailRepository extends JpaRepository<TransaksiDetail, Long> {
}
