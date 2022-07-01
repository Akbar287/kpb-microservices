package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.PenagihanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenagihanDetailRepository extends JpaRepository<PenagihanDetail, Long> {
}
