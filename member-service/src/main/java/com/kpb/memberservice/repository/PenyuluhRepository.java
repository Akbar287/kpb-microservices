package com.kpb.memberservice.repository;

import com.kpb.memberservice.domain.Penyuluh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenyuluhRepository extends JpaRepository<Penyuluh, Long> {
    Page<Penyuluh> findAllByAlamatLike(String alamat, Pageable pageable);
    Penyuluh findByUserId(Long userId);
}
