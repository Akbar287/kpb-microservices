package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.LuasLahan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuasLahanRepository extends JpaRepository<LuasLahan, Long> {
}
