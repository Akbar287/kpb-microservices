package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.ProdukDistributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukDistributorRepository extends JpaRepository<ProdukDistributor, Long> {
}
