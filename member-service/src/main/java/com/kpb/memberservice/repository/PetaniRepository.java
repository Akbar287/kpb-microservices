package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Petani;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetaniRepository extends JpaRepository<Petani, Long> {
    Page<Petani> findAllByNikLike(String nik, Pageable pageable);
    List<Petani> findAllByNikLike(String nik);
    Petani findByNik(String nik);

    Page<Petani> findAllByNikLikeAndPoktan_Kios_UserId(String nik, Long user, Pageable pageable);

    Petani findByUserId(Long userId);

    Page<Petani> findAllByNikLikeAndPoktan_UserId(String nik, Long poktanUser, Pageable pageable);
    Page<Petani> findAllByNikLikeAndPoktan_PoktanIdIn(String nik, List<Long> listPoktanId, Pageable pageable);
}
