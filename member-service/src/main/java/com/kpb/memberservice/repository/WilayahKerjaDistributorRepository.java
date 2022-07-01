package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.WilayahKerjaDistributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WilayahKerjaDistributorRepository extends JpaRepository<WilayahKerjaDistributor, Long> {
}
