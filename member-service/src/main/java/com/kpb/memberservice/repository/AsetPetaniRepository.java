package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.AsetPetani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsetPetaniRepository extends JpaRepository<AsetPetani, Long> {
}
