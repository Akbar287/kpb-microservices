package com.kpb.insuranceservice.service;

import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimListResponseDTO;

import java.util.List;

public interface FileKlaimService {
    public List<FileKlaimListResponseDTO> findAll(Long klaimId);
    public FileKlaimDetailResponseDTO findDetail(Long fileKlaimId);
    public void create(FileKlaimCreateUpdateRequestDTO dto);
    public void update(Long fileKlaimId, FileKlaimCreateUpdateRequestDTO dto);
    public void delete(Long fileKlaimId);
}
