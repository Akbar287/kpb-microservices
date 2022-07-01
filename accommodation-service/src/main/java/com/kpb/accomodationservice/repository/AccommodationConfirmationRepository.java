package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationConfirmationRepository extends JpaRepository<AccommodationConfirmation, Long> {
}
