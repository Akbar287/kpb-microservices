package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.DinasPopt;
import com.kpb.insuranceservice.domain.FileKlaim;
import com.kpb.insuranceservice.domain.Klaim;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.FileKlaim.FileKlaimListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.DinasPoptRepository;
import com.kpb.insuranceservice.repository.FileKlaimRepository;
import com.kpb.insuranceservice.repository.KlaimRepository;
import com.kpb.insuranceservice.service.FileKlaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileKlaimServiceImpl implements FileKlaimService {

    @Autowired
    private FileKlaimRepository fileKlaimRepository;

    @Autowired
    private KlaimRepository klaimRepository;

    @Autowired
    private DinasPoptRepository dinasPoptRepository;

    @Override
    public List<FileKlaimListResponseDTO> findAll(Long klaimId) {
        Klaim klaim = klaimRepository.findById(klaimId).orElseThrow(()->new ResourceNotFoundException("klaim.not.found"));

        return klaim.getFileKlaim().stream().map(k->{
            FileKlaimListResponseDTO dto = new FileKlaimListResponseDTO();
            dto.setFileKlaimId(k.getFileKlaimId());
            dto.setJenisKlaimFile(k.getJenisKlaimFile());
            dto.setNamaFile(k.getNamaFile());
            dto.setNamaDokumen(k.getNamaDokumen());
            return dto;
        }).toList();
    }

    @Override
    public FileKlaimDetailResponseDTO findDetail(Long fileKlaimId) {
        FileKlaim fileKlaim = fileKlaimRepository.findById(fileKlaimId).orElseThrow(()->new ResourceNotFoundException("file.klaim.not.found"));

        FileKlaimDetailResponseDTO dto = new FileKlaimDetailResponseDTO();
        dto.setFileKlaimId(fileKlaim.getFileKlaimId());
        dto.setNamaFile(fileKlaim.getNamaFile());
        dto.setNamaDokumen(fileKlaim.getNamaDokumen());
        dto.setJenisKlaimFile(fileKlaim.getJenisKlaimFile());
        return dto;
    }

    @Override
    public void create(FileKlaimCreateUpdateRequestDTO dto) {
        Klaim klaim = klaimRepository.findById(dto.getKlaimId()).orElseThrow(()->new ResourceNotFoundException("klaim.not.found"));
        DinasPopt dinasPopt = dinasPoptRepository.findById(dto.getDinasPoptId()).orElseThrow(()->new ResourceNotFoundException("dinas.popt.not.found"));

        FileKlaim fileKlaim = new FileKlaim();
        fileKlaim.setNamaFile(dto.getNamaFile());
        fileKlaim.setNamaDokumen(dto.getNamaDokumen());
        fileKlaim.setJenisKlaimFile(dto.getJenisKlaimFile());
        fileKlaim.setKlaim(klaim);
        fileKlaim.setDinasPopt(dinasPopt);
        fileKlaimRepository.save(fileKlaim);
    }

    @Override
    public void update(Long fileKlaimId, FileKlaimCreateUpdateRequestDTO dto) {
        FileKlaim fileKlaim = fileKlaimRepository.findById(fileKlaimId).orElseThrow(()->new ResourceNotFoundException("file.klaim.not.found"));
        fileKlaim.setNamaFile(dto.getNamaFile());
        fileKlaim.setNamaDokumen(dto.getNamaDokumen());
        fileKlaim.setJenisKlaimFile(dto.getJenisKlaimFile());
        fileKlaimRepository.save(fileKlaim);
    }

    @Override
    public void delete(Long fileKlaimId) {
        fileKlaimRepository.deleteById(fileKlaimId);
    }
}
