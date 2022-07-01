package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.Penagihan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenagihanRepository extends JpaRepository<Penagihan, Long> {

    @Query("SELECT p FROM Penagihan p join PendaftaranAsuransi pa on p = pa where pa.pendaftaranAsuransiId in :listPendaftaranAsuransiId")
    Page<Penagihan> findAllByPendaftaranAsuransiIn(@Param("listPendaftaranAsuransiId") List<Long> listPendaftaranAsuransiId, Pageable pageable);
}
