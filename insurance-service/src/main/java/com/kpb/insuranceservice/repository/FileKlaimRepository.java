package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.FileKlaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileKlaimRepository extends JpaRepository<FileKlaim, Long> {
}
