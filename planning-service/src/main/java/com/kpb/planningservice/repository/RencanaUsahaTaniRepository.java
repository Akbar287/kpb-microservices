package com.kpb.planningservice.repository;

import com.kpb.planningservice.domain.RencanaUsahaTani;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RencanaUsahaTaniRepository extends JpaRepository<RencanaUsahaTani, Long> {
    Page<RencanaUsahaTani> findAllByTahun(int tahun, Pageable pageable);
    Page<RencanaUsahaTani> findAllByTahunAndPetaniId(int tahun, Long petani, Pageable pageable);
    Page<RencanaUsahaTani> findAllByPetaniIdInAndTahun(List<Long> petaniId, int tahun, Pageable pageable);
    RencanaUsahaTani findByTahunAndPetaniId(int tahun, Long petaniId);
}
