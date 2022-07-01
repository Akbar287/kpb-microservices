package com.kpb.creditservice.repository;

import com.kpb.creditservice.domain.BusinessCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessCreditRepository extends JpaRepository<BusinessCredit, Long> {
}
