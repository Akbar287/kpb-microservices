package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationPesticideStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPesticideStatusRepository extends JpaRepository<AccommodationPesticideStatus, Long> {
}
