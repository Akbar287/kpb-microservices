package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.ScholarshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipTypeRepository extends JpaRepository<ScholarshipType, Long> {
}
