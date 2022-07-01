package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.RealisasiPupukSubsidi;
import com.kpb.planningservice.domain.RealisasiPupukSubsidiDetail;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiDetailDTO;
import com.kpb.planningservice.dto.RealisasiPupukSubsidi.RealisasiPupukSubsidiResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.RealisasiPupukSubsidiDetailRepository;
import com.kpb.planningservice.repository.RealisasiPupukSubsidiRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.RealisasiPupukSubsidiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealisasiPupukSubsidiServiceImpl implements RealisasiPupukSubsidiService {

    @Autowired
    private RealisasiPupukSubsidiRepository realisasiPupukSubsidiRepository;

    @Autowired
    private RealisasiPupukSubsidiDetailRepository realisasiPupukSubsidiDetailRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Override
    public List<RealisasiPupukSubsidiResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getRealisasiPupukSubsidi().stream().map(realisasi->{
            List<RealisasiPupukSubsidiDetailDTO> realisasiPupukSubsidiDetailDTOS = realisasi.getRealisasiPupukSubsidiDetails().stream().map(rps->{
                RealisasiPupukSubsidiDetailDTO realisasiPupukSubsidiDetailDTO = new RealisasiPupukSubsidiDetailDTO();
                realisasiPupukSubsidiDetailDTO.setRealisasiPupukSubsidiDetailId(rps.getRealisasiPupukSubsidiDetailId());
                realisasiPupukSubsidiDetailDTO.setNamaPupuk(rps.getNamaPupuk());
                realisasiPupukSubsidiDetailDTO.setJumlah(rps.getJumlah());
                return realisasiPupukSubsidiDetailDTO;
            }).toList();

            RealisasiPupukSubsidiResponseDTO realisasiPupukSubsidiResponseDTO = new RealisasiPupukSubsidiResponseDTO();
            realisasiPupukSubsidiResponseDTO.setRealisasiPupukSubsidiId(realisasi.getRealisasiPupukSubsidiId());
            realisasiPupukSubsidiResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
            realisasiPupukSubsidiResponseDTO.setMasaTanam(realisasi.getMasaTanam());
            realisasiPupukSubsidiResponseDTO.setDetails(realisasiPupukSubsidiDetailDTOS);
            return realisasiPupukSubsidiResponseDTO;
        }).toList();
    }

    @Override
    public void create( RealisasiPupukSubsidiCreateUpdateRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        RealisasiPupukSubsidi realisasiPupukSubsidi = new RealisasiPupukSubsidi();
        realisasiPupukSubsidi.setMasaTanam(dto.getMasaTanam());
        realisasiPupukSubsidi.setRencanaUsahaTani(rencanaUsahaTani);
        realisasiPupukSubsidiRepository.save(realisasiPupukSubsidi);

        List<RealisasiPupukSubsidiDetail> realisasiPupukSubsidiDetails = dto.getDetails().stream().map(b->{
            RealisasiPupukSubsidiDetail realisasiPupukSubsidiDetail = new RealisasiPupukSubsidiDetail();
            realisasiPupukSubsidiDetail.setRealisasiPupukSubsidi(realisasiPupukSubsidi);
            realisasiPupukSubsidiDetail.setNamaPupuk(b.getNamaPupuk());
            realisasiPupukSubsidiDetail.setJumlah(b.getJumlah());
            return realisasiPupukSubsidiDetail;
        }).toList();
        realisasiPupukSubsidiDetailRepository.saveAll(realisasiPupukSubsidiDetails);
    }

    @Override
    public void update(Long realisaisPupukSubsidiId, RealisasiPupukSubsidiCreateUpdateRequestDTO dto) {
        RealisasiPupukSubsidi realisasiPupukSubsidi = realisasiPupukSubsidiRepository.findById(realisaisPupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("realisasi.pupuk.not.found"));
        realisasiPupukSubsidi.setMasaTanam(dto.getMasaTanam());
        realisasiPupukSubsidiRepository.save(realisasiPupukSubsidi);

        realisasiPupukSubsidiDetailRepository.deleteAll(realisasiPupukSubsidi.getRealisasiPupukSubsidiDetails());

        List<RealisasiPupukSubsidiDetail> realisasiPupukSubsidiDetails = dto.getDetails().stream().map(b->{
            RealisasiPupukSubsidiDetail realisasiPupukSubsidiDetail = new RealisasiPupukSubsidiDetail();
            realisasiPupukSubsidiDetail.setRealisasiPupukSubsidi(realisasiPupukSubsidi);
            realisasiPupukSubsidiDetail.setNamaPupuk(b.getNamaPupuk());
            realisasiPupukSubsidiDetail.setJumlah(b.getJumlah());
            return realisasiPupukSubsidiDetail;
        }).toList();
        realisasiPupukSubsidiDetailRepository.saveAll(realisasiPupukSubsidiDetails);
    }

    @Override
    public void delete(Long realisaisPupukSubsidiId) {
        RealisasiPupukSubsidi realisasiPupukSubsidi = realisasiPupukSubsidiRepository.findById(realisaisPupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("realisasi.pupuk.not.found"));
        realisasiPupukSubsidiDetailRepository.deleteAll(realisasiPupukSubsidi.getRealisasiPupukSubsidiDetails());
        realisasiPupukSubsidiRepository.delete(realisasiPupukSubsidi);
    }
}
