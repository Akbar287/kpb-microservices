package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRegistrationRepository extends JpaRepository<AccommodationRegistration, Long> {
}
