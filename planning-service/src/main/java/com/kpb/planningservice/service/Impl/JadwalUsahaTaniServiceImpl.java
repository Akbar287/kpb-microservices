package com.kpb.planningservice.service.Impl;

import com.kpb.planningservice.domain.HasilPascaPanen;
import com.kpb.planningservice.domain.JadwalUsahaTani;
import com.kpb.planningservice.domain.PerkiraanJumlahPanen;
import com.kpb.planningservice.domain.RencanaUsahaTani;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniRequestDTO;
import com.kpb.planningservice.dto.JadwalUsahaTani.JadwalUsahaTaniResponseDTO;
import com.kpb.planningservice.exception.ResourceNotFoundException;
import com.kpb.planningservice.repository.HasilPascaPanenRepository;
import com.kpb.planningservice.repository.JadwalUsahaTaniRepository;
import com.kpb.planningservice.repository.PerkiraanJumlahPanenRepository;
import com.kpb.planningservice.repository.RencanaUsahaTaniRepository;
import com.kpb.planningservice.service.JadwalUsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;

@Service
public class JadwalUsahaTaniServiceImpl implements JadwalUsahaTaniService {

    @Autowired
    private JadwalUsahaTaniRepository jadwalUsahaTaniRepository;

    @Autowired
    private RencanaUsahaTaniRepository rencanaUsahaTaniRepository;

    @Autowired
    private PerkiraanJumlahPanenRepository perkiraanJumlahPanenRepository;

    @Autowired
    private HasilPascaPanenRepository hasilPascaPanenRepository;

    @Override
    public List<JadwalUsahaTaniResponseDTO> findDetail(Long rencanaUsahaTaniId) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(rencanaUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        return rencanaUsahaTani.getJadwalUsahaTani().stream().map(jadwal->{
            JadwalUsahaTaniResponseDTO jadwalUsahaTaniResponseDTO = new JadwalUsahaTaniResponseDTO();
            jadwalUsahaTaniResponseDTO.setJadwalUsahaTaniId(jadwal.getJadwalUsahaTaniId());
            jadwalUsahaTaniResponseDTO.setRencanaUsahaTaniId(rencanaUsahaTaniId);
            jadwalUsahaTaniResponseDTO.setMasaTanam(jadwal.getMasaTanam());
            jadwalUsahaTaniResponseDTO.setAirTanahDrainase(String.valueOf(jadwal.getAirTanahDrainase()));
            jadwalUsahaTaniResponseDTO.setPengolahanTanah(String.valueOf(jadwal.getPengolahanTanah()));
            jadwalUsahaTaniResponseDTO.setPersemaian(String.valueOf(jadwal.getPersemaian()));
            jadwalUsahaTaniResponseDTO.setPenanaman(String.valueOf(jadwal.getPenanaman()));
            jadwalUsahaTaniResponseDTO.setPemeliharaan(String.valueOf(jadwal.getPemeliharaan()));
            jadwalUsahaTaniResponseDTO.setSemprotanHerbisida(String.valueOf(jadwal.getSemprotanHerbisida()));
            jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaPertama(String.valueOf(jadwal.getPenyemprotanFungisidaPertama()));
            jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaKedua(String.valueOf(jadwal.getPenyemprotanFungisidaKedua()));
            jadwalUsahaTaniResponseDTO.setPenyemprotanFungisidaKetiga(String.valueOf(jadwal.getPenyemprotanFungisidaKetiga()));
            jadwalUsahaTaniResponseDTO.setPemupukanDasar(String.valueOf(jadwal.getPemupukanDasar()));
            jadwalUsahaTaniResponseDTO.setPemupukanPertama(String.valueOf(jadwal.getPemupukanPertama()));
            jadwalUsahaTaniResponseDTO.setPemupukanKedua(String.valueOf(jadwal.getPemupukanKedua()));
            jadwalUsahaTaniResponseDTO.setPenyemprotanInsektisidaGandaBuah(String.valueOf(jadwal.getPenyemprotanInsektisidaGandaBuah()));
            jadwalUsahaTaniResponseDTO.setPanen(String.valueOf(jadwal.getPanen()));
            jadwalUsahaTaniResponseDTO.setGabahKeringPanen(jadwal.getPerkiraanJumlahPanen().getGabahKeringPanen());
            jadwalUsahaTaniResponseDTO.setGabahKeringGiling(jadwal.getPerkiraanJumlahPanen().getGabahKeringGiling());
            jadwalUsahaTaniResponseDTO.setBeras(jadwal.getPerkiraanJumlahPanen().getBeras());
            jadwalUsahaTaniResponseDTO.setHasilPanen(jadwal.getHasilPascaPanen().getHasilPanen());
            jadwalUsahaTaniResponseDTO.setDeskripsi(jadwal.getHasilPascaPanen().getDeskripsi());
            jadwalUsahaTaniResponseDTO.setPendapatanKotor(jadwal.getHasilPascaPanen().getPendapatanKotor().intValue());
            return jadwalUsahaTaniResponseDTO;
        }).toList();
    }

    @Override
    public void create(JadwalUsahaTaniRequestDTO dto) {
        RencanaUsahaTani rencanaUsahaTani = rencanaUsahaTaniRepository.findById(dto.getRencanaUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("rut.not.found"));

        JadwalUsahaTani jadwalUsahaTani = new JadwalUsahaTani();
        jadwalUsahaTani.setRencanaUsahaTani(rencanaUsahaTani);
        jadwalUsahaTani.setMasaTanam(dto.getMasaTanam());
        jadwalUsahaTani.setAirTanahDrainase(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getAirTanahDrainase()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPengolahanTanah(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPengolahanTanah()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPersemaian(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPersemaian()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenanaman(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenanaman()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemeliharaan(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemeliharaan()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setSemprotanHerbisida(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getSemprotanHerbisida()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaPertama(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaPertama()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaKedua(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaKedua()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaKetiga(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaKetiga()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanDasar(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanDasar()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanPertama(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanPertama()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanKedua(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanKedua()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanInsektisidaGandaBuah(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanInsektisidaGandaBuah()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPanen(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPanen()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTaniRepository.save(jadwalUsahaTani);

        HasilPascaPanen hasilPascaPanen = new HasilPascaPanen();
        hasilPascaPanen.setJadwalUsahaTani(jadwalUsahaTani);
        hasilPascaPanen.setDeskripsi(dto.getDeskripsi());
        hasilPascaPanen.setHasilPanen(dto.getHasilPanen());
        hasilPascaPanen.setPendapatanKotor(BigInteger.valueOf(dto.getPendapatanKotor()));
        hasilPascaPanenRepository.save(hasilPascaPanen);

        PerkiraanJumlahPanen perkiraanJumlahPanen = new PerkiraanJumlahPanen();
        perkiraanJumlahPanen.setJadwalUsahaTani(jadwalUsahaTani);
        perkiraanJumlahPanen.setBeras(dto.getBeras());
        perkiraanJumlahPanen.setGabahKeringGiling(dto.getGabahKeringGiling());
        perkiraanJumlahPanen.setGabahKeringPanen(dto.getGabahKeringPanen());
        perkiraanJumlahPanenRepository.save(perkiraanJumlahPanen);
    }

    @Override
    public void update(Long jadwalUsahaTaniId, JadwalUsahaTaniRequestDTO dto) {
        JadwalUsahaTani jadwalUsahaTani = jadwalUsahaTaniRepository.findById(jadwalUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("jadwal.not.found"));
        jadwalUsahaTani.setMasaTanam(dto.getMasaTanam());
        jadwalUsahaTani.setAirTanahDrainase(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getAirTanahDrainase()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPengolahanTanah(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPengolahanTanah()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPersemaian(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPersemaian()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenanaman(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenanaman()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemeliharaan(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemeliharaan()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setSemprotanHerbisida(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getSemprotanHerbisida()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaPertama(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaPertama()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaKedua(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaKedua()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanFungisidaKetiga(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanFungisidaKetiga()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanDasar(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanDasar()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanPertama(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanPertama()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPemupukanKedua(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPemupukanKedua()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPenyemprotanInsektisidaGandaBuah(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPenyemprotanInsektisidaGandaBuah()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTani.setPanen(LocalDate.ofInstant(Instant.ofEpochSecond(dto.getPanen()), TimeZone.getDefault().toZoneId()));
        jadwalUsahaTaniRepository.save(jadwalUsahaTani);

        HasilPascaPanen hasilPascaPanen = jadwalUsahaTani.getHasilPascaPanen();
        hasilPascaPanen.setJadwalUsahaTani(jadwalUsahaTani);
        hasilPascaPanen.setDeskripsi(dto.getDeskripsi());
        hasilPascaPanen.setHasilPanen(dto.getHasilPanen());
        hasilPascaPanen.setPendapatanKotor(BigInteger.valueOf(dto.getPendapatanKotor()));
        hasilPascaPanenRepository.save(hasilPascaPanen);

        PerkiraanJumlahPanen perkiraanJumlahPanen = jadwalUsahaTani.getPerkiraanJumlahPanen();
        perkiraanJumlahPanen.setJadwalUsahaTani(jadwalUsahaTani);
        perkiraanJumlahPanen.setBeras(dto.getBeras());
        perkiraanJumlahPanen.setGabahKeringGiling(dto.getGabahKeringGiling());
        perkiraanJumlahPanen.setGabahKeringPanen(dto.getGabahKeringPanen());
        perkiraanJumlahPanenRepository.save(perkiraanJumlahPanen);
    }

    @Override
    public void delete(Long jadwalUsahaTaniId) {
        JadwalUsahaTani jadwalUsahaTani = jadwalUsahaTaniRepository.findById(jadwalUsahaTaniId).orElseThrow(()->new ResourceNotFoundException("jadwal.not.found"));
        perkiraanJumlahPanenRepository.delete(jadwalUsahaTani.getPerkiraanJumlahPanen());
        hasilPascaPanenRepository.delete(jadwalUsahaTani.getHasilPascaPanen());
        jadwalUsahaTaniRepository.delete(jadwalUsahaTani);
    }
}
