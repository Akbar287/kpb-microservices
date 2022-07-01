package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.DinasKabupaten;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinasKabupatenRepository extends JpaRepository<DinasKabupaten, Long> {
    Page<DinasKabupaten> findAllByNamaKetuaLike(String nama, Pageable pageable);
}
