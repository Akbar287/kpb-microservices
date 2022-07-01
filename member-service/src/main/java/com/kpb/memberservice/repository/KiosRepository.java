package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Kios;
import com.kpb.memberservice.domain.Penyuluh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KiosRepository extends JpaRepository<Kios, Long> {
    Page<Kios> findAllByKodePihcLike(String kode, Pageable pageable);
    Kios findByUserId(Long user);
    List<Kios> findAllByDistributor_DistributorId(Long distributor);
    Page<Kios> findAllByKodePihcLikeAndDistributor_UserId(String kode, Long distributor, Pageable pageable);
    Page<Kios> findAllByKodePihcLikeAndPenyuluh_UserId(String kode, Long penyuluh, Pageable pageable);

    List<Kios> findAllByPenyuluhIsNull();
    List<Kios> findAllByPenyuluh_PenyuluhId(Long penyuluhUserId);
}
