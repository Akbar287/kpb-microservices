package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.ScholarshipStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipStudentRepository extends JpaRepository<ScholarshipStudent, Long> {
}
