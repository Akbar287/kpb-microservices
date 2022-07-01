package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationPesticideRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPesticideRegistrationRepository extends JpaRepository<AccommodationPesticideRegistration, Long> {
}
