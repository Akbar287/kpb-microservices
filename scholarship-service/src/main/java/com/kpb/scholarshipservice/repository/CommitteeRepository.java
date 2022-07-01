package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {
}
