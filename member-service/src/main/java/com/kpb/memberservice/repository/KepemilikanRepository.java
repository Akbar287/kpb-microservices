package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Kepemilikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KepemilikanRepository extends JpaRepository<Kepemilikan, Long> {
}
