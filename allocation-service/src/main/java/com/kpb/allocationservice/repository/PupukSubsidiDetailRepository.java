package com.kpb.allocationservice.repository;

import com.kpb.allocationservice.domain.PupukSubsidiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupukSubsidiDetailRepository extends JpaRepository<PupukSubsidiDetail, Long> {
}
