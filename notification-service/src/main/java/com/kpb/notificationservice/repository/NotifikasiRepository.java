package com.kpb.notificationservice.repository;

import com.kpb.notificationservice.domain.Notifikasi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifikasiRepository extends JpaRepository<Notifikasi, Long> {
    Page<Notifikasi> findAllByJudulLikeAndUserId(String judul, Long userId, Pageable pageable);
}
