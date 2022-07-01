package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.BiayaUsahaTani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiayaUsahaTaniRepository extends JpaRepository<BiayaUsahaTani, Long> {
}
