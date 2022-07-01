package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.BiayaUsahaTani;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniRequestDTO;
import com.kpb.planningservice.dto.BiayaUsahaTani.BiayaTaniResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.BiayaUsahaTaniRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.BiayaUsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BiayaUsahaTaniServiceImpl implements BiayaUsahaTaniService {

    @Autowired
    private BiayaUsahaTaniRepository biayaUsahaTaniRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Override
    public List<BiayaTaniResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getBiayaUsahaTani().stream().map(b -> {
            BiayaTaniResponseDTO biayaTaniResponseDTO = new BiayaTaniResponseDTO();
            biayaTaniResponseDTO.setBiayaTaniId(b.getBiayaUsahaTaniId());
            biayaTaniResponseDTO.setRencanaUsahaTaniId(b.getBiayaUsahaTaniId());
            biayaTaniResponseDTO.setHarga(b.getHarga().intValue());
            biayaTaniResponseDTO.setJenisBiayaUsahaTani(b.getJenisBiayaUsahaTani());
            biayaTaniResponseDTO.setJenis(b.getJenis());
            biayaTaniResponseDTO.setKuantitas(b.getKuantitas());
            biayaTaniResponseDTO.setSatuan(b.getSatuan());
            return biayaTaniResponseDTO;
        }).toList();
    }

    @Override
    public void create(BiayaTaniRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        BiayaUsahaTani biayaUsahaTani = new BiayaUsahaTani();
        biayaUsahaTani.setJenisBiayaUsahaTani(dto.getJenisBiayaUsahaTani());
        biayaUsahaTani.setRencanaUsahaTani(rencanaUsahaTani);
        biayaUsahaTani.setJenis(dto.getJenis());
        biayaUsahaTani.setHarga(BigInteger.valueOf(dto.getHarga()));
        biayaUsahaTani.setKuantitas(dto.getKuantitas());
        biayaUsahaTani.setSatuan(dto.getSatuan());
        biayaUsahaTaniRepository.save(biayaUsahaTani);
    }

    @Override
    public void update(Long biayaTaniId, BiayaTaniRequestDTO dto) {
        BiayaUsahaTani biayaUsahaTani = biayaUsahaTaniRepository.findById(biayaTaniId).orElseThrow(()->new ResourceNotFoundException("biaya.usaha.tani.not.found"));
        biayaUsahaTani.setJenisBiayaUsahaTani(dto.getJenisBiayaUsahaTani());
        biayaUsahaTani.setJenis(dto.getJenis());
        biayaUsahaTani.setHarga(BigInteger.valueOf(dto.getHarga()));
        biayaUsahaTani.setKuantitas(dto.getKuantitas());
        biayaUsahaTani.setSatuan(dto.getSatuan());
        biayaUsahaTaniRepository.save(biayaUsahaTani);
    }

    @Override
    public void delete(Long biayaTaniId) {
        BiayaUsahaTani biayaUsahaTani = biayaUsahaTaniRepository.findById(biayaTaniId).orElseThrow(()->new ResourceNotFoundException("biaya.usaha.tani.not.found"));
        biayaUsahaTaniRepository.delete(biayaUsahaTani);
    }
}
