package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.MasaTanamPetani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasaTanamPetaniRepository extends JpaRepository<MasaTanamPetani, Long> {
}
