package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.JadwalUsahaTani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JadwalUsahaTaniRepository extends JpaRepository<JadwalUsahaTani, Long> {
}
