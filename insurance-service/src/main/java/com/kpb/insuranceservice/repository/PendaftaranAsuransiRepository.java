package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendaftaranAsuransiRepository extends JpaRepository<PendaftaranAsuransi, Long> {
    Page<PendaftaranAsuransi> findAllByNomorAsuransiLike(String search, Pageable pageable);
    List<PendaftaranAsuransi> findAllByNomorAsuransiLike(String search);
    List<PendaftaranAsuransi> findAllByTahunAndMasaTanam(int tahun, int masa_tanam);
}
