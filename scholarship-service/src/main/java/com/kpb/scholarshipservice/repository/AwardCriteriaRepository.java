package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.AwardCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardCriteriaRepository extends JpaRepository<AwardCriteria, Long> {
}
