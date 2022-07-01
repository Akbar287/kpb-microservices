package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.KebutuhanSaprotan;
import com.kpb.planningservice.domain.KebutuhanSaprotanDetail;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanDetailDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanRequestDTO;
import com.kpb.planningservice.dto.KebutuhanSaprotan.KebutuhanSaprotanResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.KebutuhanSaprotanDetailRepository;
import com.kpb.planningservice.repository.KebutuhanSaprotanRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.KebutuhanSaprotanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KebutuhanSaprotanServiceImpl implements KebutuhanSaprotanService {

    @Autowired
    private KebutuhanSaprotanRepository kebutuhanSaprotanRepository;

    @Autowired
    private KebutuhanSaprotanDetailRepository kebutuhanSaprotanDetailRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Override
    public List<KebutuhanSaprotanResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getKebutuhanSaprotan().stream().map(kebutuhan->{
            List<KebutuhanSaprotanDetailDTO> kebutuhanSaprotanDetailDTO = kebutuhan.getKebutuhanSaprotanDetails().stream().map(detailKebutuhan->{
                KebutuhanSaprotanDetailDTO ks = new KebutuhanSaprotanDetailDTO();
                ks.setKebutuhanSaprotanDetailId(detailKebutuhan.getKebutuhanSaprotanDetailId());
                ks.setNamaSaprotan(detailKebutuhan.getNamaSaprotan());
                ks.setJumlah(detailKebutuhan.getJumlah());
                return ks;
            }).toList();
            KebutuhanSaprotanResponseDTO kebutuhanSaprotanResponseDTO = new KebutuhanSaprotanResponseDTO();
            kebutuhanSaprotanResponseDTO.setKebutuhanSaprotanId(kebutuhan.getKebutuhanSaprotanId());
            kebutuhanSaprotanResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
            kebutuhanSaprotanResponseDTO.setJenisSaprotan(kebutuhan.getJenisSaprotan());
            kebutuhanSaprotanResponseDTO.setMasaTanam(kebutuhan.getMasaTanam());
            kebutuhanSaprotanResponseDTO.setDetails(kebutuhanSaprotanDetailDTO);
            return kebutuhanSaprotanResponseDTO;
        }).toList();
    }

    @Override
    public void create( KebutuhanSaprotanRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        KebutuhanSaprotan kebutuhanSaprotan = new KebutuhanSaprotan();
        kebutuhanSaprotan.setRencanaUsahaTani(rencanaUsahaTani);
        kebutuhanSaprotan.setJenisSaprotan(dto.getJenisSaprotan());
        kebutuhanSaprotan.setMasaTanam(dto.getMasaTanam());
        kebutuhanSaprotanRepository.save(kebutuhanSaprotan);

        List<KebutuhanSaprotanDetail> kebutuhanSaprotanDetails = dto.getDetails().stream().map(b->{
            KebutuhanSaprotanDetail kebutuhanSaprotanDetail = new KebutuhanSaprotanDetail();
            kebutuhanSaprotanDetail.setKebutuhanSaprotan(kebutuhanSaprotan);
            kebutuhanSaprotanDetail.setNamaSaprotan(b.getNamaSaprotan());
            kebutuhanSaprotanDetail.setJumlah(b.getJumlah());
            return kebutuhanSaprotanDetail;
        }).toList();
        kebutuhanSaprotanDetailRepository.saveAll(kebutuhanSaprotanDetails);
    }

    @Override
    public void update(Long kebutuhanSaprotanId, KebutuhanSaprotanRequestDTO dto) {
        KebutuhanSaprotan kebutuhanSaprotan = kebutuhanSaprotanRepository.findById(kebutuhanSaprotanId).orElseThrow(()->new ResourceNotFoundException("kebutuhan.not.found"));
        kebutuhanSaprotan.setJenisSaprotan(dto.getJenisSaprotan());
        kebutuhanSaprotan.setMasaTanam(dto.getMasaTanam());
        kebutuhanSaprotanRepository.save(kebutuhanSaprotan);

        kebutuhanSaprotanDetailRepository.deleteAll(kebutuhanSaprotan.getKebutuhanSaprotanDetails());

        List<KebutuhanSaprotanDetail> kebutuhanSaprotanDetails = dto.getDetails().stream().map(b->{
            KebutuhanSaprotanDetail kebutuhanSaprotanDetail = new KebutuhanSaprotanDetail();
            kebutuhanSaprotanDetail.setKebutuhanSaprotan(kebutuhanSaprotan);
            kebutuhanSaprotanDetail.setNamaSaprotan(b.getNamaSaprotan());
            kebutuhanSaprotanDetail.setJumlah(b.getJumlah());
            return kebutuhanSaprotanDetail;
        }).toList();
        kebutuhanSaprotanDetailRepository.saveAll(kebutuhanSaprotanDetails);
    }

    @Override
    public void delete(Long kebutuhanSaprotanId) {
        KebutuhanSaprotan kebutuhanSaprotan = kebutuhanSaprotanRepository.findById(kebutuhanSaprotanId).orElseThrow(()->new ResourceNotFoundException("kebutuhan.not.found"));
        kebutuhanSaprotanDetailRepository.deleteAll(kebutuhanSaprotan.getKebutuhanSaprotanDetails());
        kebutuhanSaprotanRepository.delete(kebutuhanSaprotan);
    }
}
