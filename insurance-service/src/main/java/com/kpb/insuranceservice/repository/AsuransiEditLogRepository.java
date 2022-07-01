package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.AsuransiEditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsuransiEditLogRepository extends JpaRepository<AsuransiEditLog, Long> {
}
