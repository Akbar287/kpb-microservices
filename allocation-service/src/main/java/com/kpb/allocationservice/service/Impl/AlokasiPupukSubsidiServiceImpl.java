package com.kpb.allocationservice.service.Impl;

import com.kpb.allocationservice.domain.AlokasiPupukSubsidi;
import com.kpb.allocationservice.domain.PupukSubsidi;
import com.kpb.allocationservice.domain.PupukSubsidiStatus;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.AlokasiPupukSubsidiCreateUpdateRequestDTO;
import com.kpb.allocationservice.dto.AlokasiPupukSubsidi.NamaPupukDanJumlahHelper;
import com.kpb.allocationservice.exception.ResourceNotFoundException;
import com.kpb.allocationservice.repository.AlokasiPupukSubsidiRepository;
import com.kpb.allocationservice.repository.PupukSubsidiRepository;
import com.kpb.allocationservice.repository.PupukSubsidiStatusRepository;
import com.kpb.allocationservice.service.AlokasiPupukSubsidiService;
import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.web.Alokasi.AlokasiPupukSubsidiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

@Service @Slf4j
public class AlokasiPupukSubsidiServiceImpl implements AlokasiPupukSubsidiService {

    @Autowired
    private AlokasiPupukSubsidiRepository alokasiPupukSubsidiRepository;

    @Autowired
    private PupukSubsidiRepository pupukSubsidiRepository;

    @Autowired
    private PupukSubsidiStatusRepository pupukSubsidiStatusRepository;

    @Override
    public AlokasiPupukSubsidiResponse getSisaAlokasiPubersNyTransaksi(Long transaksiDetailId) {
        AlokasiPupukSubsidi alokasiPupukSubsidi = alokasiPupukSubsidiRepository.findByTransaksiId(transaksiDetailId);
        AlokasiPupukSubsidiResponse alokasiPupukSubsidiResponse = new AlokasiPupukSubsidiResponse();
        alokasiPupukSubsidiResponse.setAlokasiPupukSubsidiId(alokasiPupukSubsidi.getAlokasiPupukSubsidiId());
        alokasiPupukSubsidiResponse.setNamaPupuk(alokasiPupukSubsidi.getNamaPupuk());
        alokasiPupukSubsidiResponse.setJumlah(alokasiPupukSubsidi.getJumlah());
        return alokasiPupukSubsidiResponse;
    }

    @Override
    public void create(AlokasiPupukSubsidiCreateUpdateRequestDTO dto) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findByTahunAndMasaTanamAndPetaniId(dto.getTahun(), dto.getMasaTanam(), dto.getPetaniId());

        AlokasiPupukSubsidi alokasiPupukSubsidi = new AlokasiPupukSubsidi();
        alokasiPupukSubsidi.setNamaPupuk(dto.getNamaPupuk());
        alokasiPupukSubsidi.setJumlah(dto.getJumlah());
        alokasiPupukSubsidi.setPupukSubsidi(pupukSubsidi);
        alokasiPupukSubsidi.setTransaksiId(dto.getTransaksiId());
        alokasiPupukSubsidiRepository.save(alokasiPupukSubsidi);

        PupukSubsidiStatus pupukSubsidiStatus = new PupukSubsidiStatus();
        pupukSubsidiStatus.setLabel("Penebusan Dibuat");
        pupukSubsidiStatus.setPesan("Penebusan telah dilakukan untuk produk " + dto.getNamaPupuk() + " dengan jumlah " + dto.getJumlah());
        pupukSubsidiStatus.setPupukSubsidi(pupukSubsidi);
        pupukSubsidiStatus.setCreatedAt(LocalDate.now());
        pupukSubsidiStatusRepository.save(pupukSubsidiStatus);
    }

    @Override
    public void update(Long alokasiPupukSubsidiId, AlokasiPupukSubsidiCreateUpdateRequestDTO dto) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findByTahunAndMasaTanamAndPetaniId(dto.getTahun(), dto.getMasaTanam(), dto.getPetaniId());

        AlokasiPupukSubsidi alokasiPupukSubsidi = alokasiPupukSubsidiRepository.findById(alokasiPupukSubsidiId).orElseThrow(()->new ResourceNotFoundException("alokasi.not.found"));
        alokasiPupukSubsidi.setNamaPupuk(dto.getNamaPupuk());
        alokasiPupukSubsidi.setJumlah(dto.getJumlah());
        alokasiPupukSubsidi.setPupukSubsidi(pupukSubsidi);
        alokasiPupukSubsidi.setTransaksiId(dto.getTransaksiId());
        alokasiPupukSubsidiRepository.save(alokasiPupukSubsidi);
    }

    @Override
    public void delete(AlokasiPupukSubsidiCreateUpdateRequestDTO dto) {
        PupukSubsidi pupukSubsidi = pupukSubsidiRepository.findByTahunAndMasaTanamAndPetaniId(dto.getTahun(), dto.getMasaTanam(), dto.getPetaniId());

        List<Long> alokasiDelete = new ArrayList<>();
        pupukSubsidi.getAlokasiPupukSubsidi().forEach(b->{
            if (b.getNamaPupuk().equals(dto.getNamaPupuk()) && b.getJumlah() == dto.getJumlah()) {
                alokasiDelete.add(b.getAlokasiPupukSubsidiId());
            }
        });
        alokasiPupukSubsidiRepository.deleteAllById(alokasiDelete);

        PupukSubsidiStatus pupukSubsidiStatus = new PupukSubsidiStatus();
        pupukSubsidiStatus.setLabel("Penebusan Dibatalkan");
        pupukSubsidiStatus.setPesan("Penebusan telah dibatalkan untuk produk " + dto.getNamaPupuk() + " dengan jumlah " + dto.getJumlah());
        pupukSubsidiStatus.setPupukSubsidi(pupukSubsidi);
        pupukSubsidiStatus.setCreatedAt(LocalDate.now());
        pupukSubsidiStatusRepository.save(pupukSubsidiStatus);
    }
}
