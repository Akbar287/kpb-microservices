package com.kpb.notificationservice.service;

import com.kpb.notificationservice.dto.Notifikasi.NotifikasiRequestDTO;
import com.kpb.notificationservice.dto.Notifikasi.NotifikasiResponseDTO;
import com.kpb.notificationservice.dto.PaginationResponse;

import java.util.List;

public interface NotifikasiService {
    PaginationResponse findAll(int page, int size, String sort, String search, Long userId);
    NotifikasiResponseDTO findDetail(Long notifikasiId);
    void create(NotifikasiRequestDTO dto);
    void update(Long notifikasiId, NotifikasiRequestDTO dto);
    void delete(Long notifikasiId);
}
