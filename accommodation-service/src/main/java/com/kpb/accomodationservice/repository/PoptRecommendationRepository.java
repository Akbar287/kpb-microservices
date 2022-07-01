package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.PoptRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoptRecommendationRepository extends JpaRepository<PoptRecommendation, Long> {
}
