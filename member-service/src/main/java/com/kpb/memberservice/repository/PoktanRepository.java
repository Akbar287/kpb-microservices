package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Poktan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoktanRepository extends JpaRepository<Poktan, Long> {
    Page<Poktan> findAllByNamaPoktanLike(String nama, Pageable pageable);
    Page<Poktan> findAllByNamaPoktanLikeAndKios_UserId(String nama, Long user,Pageable pageable);
    Page<Poktan> findAllByNamaPoktanLikeAndKios_KiosIdIn(String nama, List<Long> listkios,Pageable pageable);
    Poktan findByUserId(Long user);
    List<Poktan> findAllByUserIdIn(List<Long> poktanUserId);
}
