package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationPesticideConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPesticideConfirmationRepository extends JpaRepository<AccommodationPesticideConfirmation, Long> {
}
