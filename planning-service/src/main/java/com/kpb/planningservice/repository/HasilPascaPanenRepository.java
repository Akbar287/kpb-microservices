package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.HasilPascaPanen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasilPascaPanenRepository extends JpaRepository<HasilPascaPanen, Long> {
}
