package com.kpb.accomodationservice.repository;

import com.kpb.accomodationservice.domain.AccommodationArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationAreaRepository extends JpaRepository<AccommodationArea, Long> {
}
