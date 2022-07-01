package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.TanggunganPetani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanggunganPetaniRepository extends JpaRepository<TanggunganPetani, Long> {
}
