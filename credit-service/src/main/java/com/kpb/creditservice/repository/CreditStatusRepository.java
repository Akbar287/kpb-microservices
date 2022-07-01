package com.kpb.creditservice.repository;

import com.kpb.creditservice.domain.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditStatusRepository extends JpaRepository<CreditStatus, Long> {
}
