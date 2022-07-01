package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.ApplicantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantStatusRepository extends JpaRepository<ApplicantStatus, Long> {
}
