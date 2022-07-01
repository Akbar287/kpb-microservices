package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.WilayahKerjaKios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WilayahKerjaKiosRepository extends JpaRepository<WilayahKerjaKios, Long> {
}
