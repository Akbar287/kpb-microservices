package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.ScholarshipRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRoundRepository extends JpaRepository<ScholarshipRound, Long> {
}
