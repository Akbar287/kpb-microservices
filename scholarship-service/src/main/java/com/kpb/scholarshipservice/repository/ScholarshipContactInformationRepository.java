package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.ScholarshipContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipContactInformationRepository  extends JpaRepository<ScholarshipContactInformation, Long> {
}
