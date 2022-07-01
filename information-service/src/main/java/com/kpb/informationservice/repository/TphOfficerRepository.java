package com.kpb.informationservice.repository;

import com.kpb.informationservice.domain.TphOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TphOfficerRepository extends JpaRepository<TphOfficer, Long> {
}
