package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationPesticideArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPesticideAreaRepository extends JpaRepository<AccommodationPesticideArea, Long> {
}
