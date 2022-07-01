package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.KekuranganPupukDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KekuranganPupukDetailRepository extends JpaRepository<KekuranganPupukDetail, Long> {
}
