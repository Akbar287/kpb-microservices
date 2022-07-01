package com.kpb.notificationservice.service.Impl;

import com.kpb.notificationservice.domain.AttributNotifikasi;
import com.kpb.notificationservice.domain.Notifikasi;
import com.kpb.notificationservice.domain.StatusNotifikasi;
import com.kpb.notificationservice.dto.Attribut.AttributeResponseDTO;
import com.kpb.notificationservice.dto.Notifikasi.NotifikasiRequestDTO;
import com.kpb.notificationservice.dto.Notifikasi.NotifikasiResponseDTO;
import com.kpb.notificationservice.dto.PaginationResponse;
import com.kpb.notificationservice.exception.ResourceNotFoundException;
import com.kpb.notificationservice.repository.AttributNotifikasiRepository;
import com.kpb.notificationservice.repository.NotifikasiRepository;
import com.kpb.notificationservice.repository.StatusNotifikasiRepository;
import com.kpb.notificationservice.service.NotifikasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotifikasiServiceImpl implements NotifikasiService {

    @Autowired
    private NotifikasiRepository notifikasiRepository;

    @Autowired
    private AttributNotifikasiRepository attributNotifikasiRepository;

    @Autowired
    private StatusNotifikasiRepository statusNotifikasiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search, Long userId) {
        sort = (sort != null) ? sort : "createdAt";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<Notifikasi> notifikasi = notifikasiRepository.findAllByJudulLikeAndUserId(search, userId, pageable);
        List<NotifikasiResponseDTO> notifikasiResponseDTOS = notifikasi.stream().map(b->{
            AttributeResponseDTO attr = new AttributeResponseDTO();
            attr.setAttributeNotifikasiId(b.getAttributNotifikasi().getAttributeNotifikasiId());
            attr.setIcon(b.getAttributNotifikasi().getIcon());
            attr.setJenis(b.getAttributNotifikasi().getJenis());
            attr.setRole(b.getAttributNotifikasi().getRole());
            attr.setRead(b.getAttributNotifikasi().isRead());

            NotifikasiResponseDTO dto = new NotifikasiResponseDTO();
            dto.setNotifikasiId(b.getNotifikasiId());
            dto.setUserId(b.getUserId());
            dto.setJudul(b.getJudul());
            dto.setPesan(b.getPesan());
            dto.setCreatedAt(String.valueOf(b.getCreatedAt()));
            dto.setUpdatedAt(String.valueOf(b.getUpdatedAt()));
            dto.setAttribute(attr);
            dto.setStatus(b.getStatusNotifikasi());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(notifikasiResponseDTOS);
        paginationResponse.setEmpty(notifikasi.isEmpty());
        paginationResponse.setFirst(notifikasi.isFirst());
        paginationResponse.setLast(notifikasi.isLast());
        paginationResponse.setNumber(notifikasi.getNumber());
        paginationResponse.setPageable(notifikasi.getPageable());
        paginationResponse.setSize(notifikasi.getSize());
        paginationResponse.setSort(notifikasi.getSort());
        paginationResponse.setTotalElements(notifikasi.getTotalElements());
        paginationResponse.setNumberOfElements(notifikasi.getNumberOfElements());
        paginationResponse.setTotalPages(notifikasi.getTotalPages());
        return paginationResponse;
    }

    @Override
    public NotifikasiResponseDTO findDetail(Long notifikasiId) {
        Notifikasi notifikasi = notifikasiRepository.findById(notifikasiId).orElseThrow(()->new ResourceNotFoundException("notifikasi.not.found"));

        AttributeResponseDTO attr = new AttributeResponseDTO();
        attr.setAttributeNotifikasiId(notifikasi.getAttributNotifikasi().getAttributeNotifikasiId());
        attr.setIcon(notifikasi.getAttributNotifikasi().getIcon());
        attr.setJenis(notifikasi.getAttributNotifikasi().getJenis());
        attr.setRole(notifikasi.getAttributNotifikasi().getRole());
        attr.setRead(notifikasi.getAttributNotifikasi().isRead());

        NotifikasiResponseDTO dto = new NotifikasiResponseDTO();
        dto.setNotifikasiId(notifikasi.getNotifikasiId());
        dto.setUserId(notifikasi.getUserId());
        dto.setJudul(notifikasi.getJudul());
        dto.setPesan(notifikasi.getPesan());
        dto.setCreatedAt(String.valueOf(notifikasi.getCreatedAt()));
        dto.setUpdatedAt(String.valueOf(notifikasi.getUpdatedAt()));
        dto.setAttribute(attr);
        dto.setStatus(notifikasi.getStatusNotifikasi());
        return dto;
    }

    @Override
    public void create(NotifikasiRequestDTO dto) {
        Notifikasi notifikasi = new Notifikasi();
        notifikasi.setUserId(dto.getUserId());
        notifikasi.setJudul(dto.getJudul());
        notifikasi.setPesan(dto.getPesan());
        notifikasi.setCreatedAt(LocalDate.now());
        notifikasi.setUpdatedAt(LocalDate.now());
        notifikasiRepository.save(notifikasi);

        AttributNotifikasi attributNotifikasi = new AttributNotifikasi();
        attributNotifikasi.setNotifikasi(notifikasi);
        attributNotifikasi.setIcon(dto.getAttribute().getIcon());
        attributNotifikasi.setJenis(dto.getAttribute().getJenis());
        attributNotifikasi.setRead(dto.getAttribute().isRead());
        attributNotifikasi.setRole(dto.getAttribute().getRole());
        attributNotifikasiRepository.save(attributNotifikasi);

        StatusNotifikasi statusNotifikasi = new StatusNotifikasi();
        statusNotifikasi.setLabel("Dibuat");
        statusNotifikasi.setValue("Pesan telah dibuat");
        statusNotifikasi.setCreated_at(LocalDate.now());
        statusNotifikasi.setNotifikasi(notifikasi);
        statusNotifikasiRepository.save(statusNotifikasi);
    }

    @Override
    public void update(Long notifikasiId, NotifikasiRequestDTO dto) {
        Notifikasi notifikasi = notifikasiRepository.findById(notifikasiId).orElseThrow(()->new ResourceNotFoundException("notifikasi.not.found"));

        AttributNotifikasi attributNotifikasi = notifikasi.getAttributNotifikasi();
        attributNotifikasi.setRead(true);
        attributNotifikasiRepository.save(attributNotifikasi);

        StatusNotifikasi statusNotifikasi = new StatusNotifikasi();
        statusNotifikasi.setLabel("Dibaca");
        statusNotifikasi.setValue("Pesan telah dibaca");
        statusNotifikasi.setCreated_at(LocalDate.now());
        statusNotifikasi.setNotifikasi(notifikasi);
        statusNotifikasiRepository.save(statusNotifikasi);
    }

    @Override
    public void delete(Long notifikasiId) {
        Notifikasi notifikasi = notifikasiRepository.findById(notifikasiId).orElseThrow(()->new ResourceNotFoundException("notifikasi.not.found"));
        attributNotifikasiRepository.delete(notifikasi.getAttributNotifikasi());
        statusNotifikasiRepository.deleteAll(notifikasi.getStatusNotifikasi());
        notifikasiRepository.delete(notifikasi);
    }
}
