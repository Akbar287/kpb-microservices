package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.KekuranganPupuk;
import com.kpb.planningservice.domain.KekuranganPupukDetail;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukDetailDTO;
import com.kpb.planningservice.dto.KekuranganPupuk.KekuranganPupukResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.KekuranganPupukDetailRepository;
import com.kpb.planningservice.repository.KekuranganPupukRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.KekuranganPupukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KekuranganPupukServiceImpl implements KekuranganPupukService {

    @Autowired
    private KekuranganPupukRepository kekuranganPupukRepository;

    @Autowired
    private KekuranganPupukDetailRepository kekuranganPupukDetailRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Override
    public List<KekuranganPupukResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getKekuranganPupuk().stream().map(kekurangan->{
            List<KekuranganPupukDetailDTO> kekuranganPupukDetailDTOS = kekurangan.getKekuranganPupukDetails().stream().map(kpd -> {
                KekuranganPupukDetailDTO kekuranganPupukDetailDTO = new KekuranganPupukDetailDTO();
                kekuranganPupukDetailDTO.setKekuranganPupukDetailId(kpd.getKekuranganPupukDetaiId());
                kekuranganPupukDetailDTO.setNamaPupuk(kpd.getNamaPupuk());
                kekuranganPupukDetailDTO.setJumlah(kpd.getJumlah());
                return kekuranganPupukDetailDTO;
            }).toList();

            KekuranganPupukResponseDTO kekuranganPupukResponseDTO = new KekuranganPupukResponseDTO();
            kekuranganPupukResponseDTO.setKekuranganPupukId(kekurangan.getKekuranganPupukId());
            kekuranganPupukResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
            kekuranganPupukResponseDTO.setMasaTanam(kekurangan.getMasaTanam());
            kekuranganPupukResponseDTO.setDetails(kekuranganPupukDetailDTOS);
            return kekuranganPupukResponseDTO;
        }).toList();
    }

    @Override
    public void create( KekuranganPupukCreateUpdateRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        KekuranganPupuk kekuranganPupuk = new KekuranganPupuk();
        kekuranganPupuk.setMasaTanam(dto.getMasaTanam());
        kekuranganPupuk.setRencanaUsahaTani(rencanaUsahaTani);
        kekuranganPupukRepository.save(kekuranganPupuk);

        List<KekuranganPupukDetail> kekuranganPupukDetails = dto.getDetails().stream().map(b->{
            KekuranganPupukDetail kekuranganPupukDetail = new KekuranganPupukDetail();
            kekuranganPupukDetail.setKekuranganPupuk(kekuranganPupuk);
            kekuranganPupukDetail.setNamaPupuk(b.getNamaPupuk());
            kekuranganPupukDetail.setJumlah(b.getJumlah());
            return kekuranganPupukDetail;
        }).toList();
        kekuranganPupukDetailRepository.saveAll(kekuranganPupukDetails);
    }

    @Override
    public void update(Long kekuranganPupukId, KekuranganPupukCreateUpdateRequestDTO dto) {
        KekuranganPupuk kekuranganPupuk = kekuranganPupukRepository.findById(kekuranganPupukId).orElseThrow(()->new ResourceNotFoundException("kekurangan.pupuk.not.found"));
        kekuranganPupuk.setMasaTanam(dto.getMasaTanam());
        kekuranganPupukRepository.save(kekuranganPupuk);

        kekuranganPupukDetailRepository.deleteAll(kekuranganPupuk.getKekuranganPupukDetails());

        List<KekuranganPupukDetail> kekuranganPupukDetails = dto.getDetails().stream().map(b->{
            KekuranganPupukDetail kekuranganPupukDetail = new KekuranganPupukDetail();
            kekuranganPupukDetail.setKekuranganPupuk(kekuranganPupuk);
            kekuranganPupukDetail.setNamaPupuk(b.getNamaPupuk());
            kekuranganPupukDetail.setJumlah(b.getJumlah());
            return kekuranganPupukDetail;
        }).toList();
        kekuranganPupukDetailRepository.saveAll(kekuranganPupukDetails);
    }

    @Override
    public void delete(Long kekuranganPupukId) {
        KekuranganPupuk kekuranganPupuk = kekuranganPupukRepository.findById(kekuranganPupukId).orElseThrow(()->new ResourceNotFoundException("kekurangan.pupuk.not.found"));
        kekuranganPupukDetailRepository.deleteAll(kekuranganPupuk.getKekuranganPupukDetails());
        kekuranganPupukRepository.delete(kekuranganPupuk);
    }
}
