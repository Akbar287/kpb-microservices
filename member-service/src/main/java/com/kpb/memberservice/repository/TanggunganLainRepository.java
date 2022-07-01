package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.TanggunganLain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TanggunganLainRepository extends JpaRepository<TanggunganLain, Long> {
}
