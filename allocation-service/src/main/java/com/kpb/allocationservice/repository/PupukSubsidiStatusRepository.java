package com.kpb.allocationservice.repository;

import com.kpb.allocationservice.domain.PupukSubsidiStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupukSubsidiStatusRepository extends JpaRepository<PupukSubsidiStatus, Long> {
}
