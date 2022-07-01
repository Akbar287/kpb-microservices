package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.RealisasiPupukSubsidiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisasiPupukSubsidiDetailRepository extends JpaRepository<RealisasiPupukSubsidiDetail, Long> {
}
