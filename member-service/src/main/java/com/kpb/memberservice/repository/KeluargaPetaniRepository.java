package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.KeluargaPetani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeluargaPetaniRepository extends JpaRepository<KeluargaPetani, Long> {
}
