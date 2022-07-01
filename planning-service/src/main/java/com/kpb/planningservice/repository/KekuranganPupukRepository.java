package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.KekuranganPupuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KekuranganPupukRepository extends JpaRepository<KekuranganPupuk, Long> {
}
