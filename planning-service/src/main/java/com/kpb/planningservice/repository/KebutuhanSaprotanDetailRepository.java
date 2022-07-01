package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.KebutuhanSaprotanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KebutuhanSaprotanDetailRepository extends JpaRepository<KebutuhanSaprotanDetail, Long> {
}
