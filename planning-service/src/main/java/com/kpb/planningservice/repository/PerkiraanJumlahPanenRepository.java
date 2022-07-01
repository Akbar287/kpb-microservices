package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.PerkiraanJumlahPanen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerkiraanJumlahPanenRepository extends JpaRepository<PerkiraanJumlahPanen, Long> {
}
