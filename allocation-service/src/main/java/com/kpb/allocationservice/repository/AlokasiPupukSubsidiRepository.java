package com.kpb.allocationservice.repository;

import com.kpb.allocationservice.domain.AlokasiPupukSubsidi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlokasiPupukSubsidiRepository extends JpaRepository<AlokasiPupukSubsidi, Long> {
    AlokasiPupukSubsidi findByTransaksiId(Long transaksiDetailId);
}
