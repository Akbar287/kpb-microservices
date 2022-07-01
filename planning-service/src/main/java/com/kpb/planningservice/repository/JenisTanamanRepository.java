package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.JenisTanaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisTanamanRepository extends JpaRepository<JenisTanaman, Long> {
}
