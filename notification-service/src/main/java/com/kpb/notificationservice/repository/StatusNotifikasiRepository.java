package com.kpb.notificationservice.repository;

import com.kpb.notificationservice.domain.StatusNotifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusNotifikasiRepository extends JpaRepository<StatusNotifikasi, Long> {
}
