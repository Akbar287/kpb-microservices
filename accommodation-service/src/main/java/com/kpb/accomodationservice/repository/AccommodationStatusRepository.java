package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatusRepository extends JpaRepository<AccommodationStatus, Long> {
}
