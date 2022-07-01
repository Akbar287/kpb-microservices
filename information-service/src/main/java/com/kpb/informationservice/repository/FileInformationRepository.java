package com.kpb.informationservice.repository;

import com.kpb.informationservice.domain.FileInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInformationRepository extends JpaRepository<FileInformation, Long> {
}
