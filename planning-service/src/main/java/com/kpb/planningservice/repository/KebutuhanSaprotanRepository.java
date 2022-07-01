package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.KebutuhanSaprotan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KebutuhanSaprotanRepository extends JpaRepository<KebutuhanSaprotan, Long> {
}
