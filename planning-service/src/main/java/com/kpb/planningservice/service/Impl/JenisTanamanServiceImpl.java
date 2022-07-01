package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.JenisTanaman;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanRequestDTO;
import com.kpb.planningservice.dto.JenisTanaman.JenisTanamanResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.JenisTanamanRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.JenisTanamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenisTanamanServiceImpl implements JenisTanamanService {

    @Autowired
    private JenisTanamanRepository jenisTanamanRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Override
    public List<JenisTanamanResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getJenisTanaman().stream().map(jenis->{
            JenisTanamanResponseDTO jenisTanamanResponseDTO = new JenisTanamanResponseDTO();
            jenisTanamanResponseDTO.setJenisTanamanId(jenis.getJenisTanamanId());
            jenisTanamanResponseDTO.setNamaTanaman(jenis.getNamaTanaman());
            jenisTanamanResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
            jenisTanamanResponseDTO.setMasaTanam(jenis.getMasaTanam());
            return  jenisTanamanResponseDTO;
        }).toList();
    }

    @Override
    public void create(JenisTanamanRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        JenisTanaman jenisTanaman = new JenisTanaman();
        jenisTanaman.setNamaTanaman(dto.getNamaTanaman());
        jenisTanaman.setRencanaUsahaTani(rencanaUsahaTani);
        jenisTanaman.setMasaTanam(dto.getMasaTanam());
        jenisTanamanRepository.save(jenisTanaman);
    }

    @Override
    public void update(Long jenisTanamanId, JenisTanamanRequestDTO dto) {
        JenisTanaman jenisTanaman = jenisTanamanRepository.findById(jenisTanamanId).orElseThrow(()->new ResourceNotFoundException("jenis.tanaman.not.found"));
        jenisTanaman.setNamaTanaman(dto.getNamaTanaman());
        jenisTanaman.setMasaTanam(dto.getMasaTanam());
        jenisTanamanRepository.save(jenisTanaman);
    }

    @Override
    public void delete(Long jenisTanamanId) {
        JenisTanaman jenisTanaman = jenisTanamanRepository.findById(jenisTanamanId).orElseThrow(()->new ResourceNotFoundException("jenis.tanaman.not.found"));
        jenisTanamanRepository.delete(jenisTanaman);
    }
}
