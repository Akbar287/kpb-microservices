package com.kpb.authservice.repository;

import com.kpb.authservice.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AkunBankRepository extends JpaRepository<BankAccount, Long> {
}
