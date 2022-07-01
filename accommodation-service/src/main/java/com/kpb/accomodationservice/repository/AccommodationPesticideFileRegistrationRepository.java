package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationPesticideFileRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPesticideFileRegistrationRepository extends JpaRepository<AccommodationPesticideFileRegistration, Long> {
}
