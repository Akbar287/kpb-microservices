package com.kpb.notificationservice.repository;

import com.kpb.notificationservice.domain.AttributNotifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributNotifikasiRepository extends JpaRepository<AttributNotifikasi, Long> {
}
