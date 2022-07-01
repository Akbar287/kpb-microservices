package com.kpb.scholarshipservice.repository;

import com.kpb.scholarshipservice.domain.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
}
