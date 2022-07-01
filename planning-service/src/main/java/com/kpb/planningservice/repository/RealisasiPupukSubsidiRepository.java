package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.RealisasiPupukSubsidi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisasiPupukSubsidiRepository extends JpaRepository<RealisasiPupukSubsidi, Long> {
}
