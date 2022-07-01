package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.PenagihanBankDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenagihanBankDetailRepository extends JpaRepository<PenagihanBankDetail, Long> {
    Page<PenagihanBankDetail> findAllByNomorRekeningLike(String nomorRekening, Pageable pageable);
}
