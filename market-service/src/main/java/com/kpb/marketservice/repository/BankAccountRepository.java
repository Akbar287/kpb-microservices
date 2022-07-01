package com.kpb.marketservice.repository;

import com.kpb.marketservice.domain.BankAcccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAcccount, Long> {
}
