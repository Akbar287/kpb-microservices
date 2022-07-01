package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Harga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HargaRepository extends JpaRepository<Harga, Long> {
}
