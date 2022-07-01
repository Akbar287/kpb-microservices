package com.kpb.insuranceservice.service.Impl;

import com.kpb.insuranceservice.domain.Klaim;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.domain.UsahaTani;
import com.kpb.insuranceservice.dto.Klaim.KlaimCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimDetailResponseDTO;
import com.kpb.insuranceservice.dto.Klaim.KlaimListResponseDTO;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.KlaimRepository;
import com.kpb.insuranceservice.repository.PendaftaranAsuransiRepository;
import com.kpb.insuranceservice.repository.UsahaTaniRepository;
import com.kpb.insuranceservice.service.KlaimService;
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
public class KlaimServiceImpl implements KlaimService {

    @Autowired
    private KlaimRepository klaimRepository;

    @Autowired
    private PendaftaranAsuransiRepository pendaftaranAsuransiRepository;

    @Autowired
    private UsahaTaniRepository usahaTaniRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "klaim";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Klaim> klaims = klaimRepository.findAllByKodeLike(search, pageable);
        return this.paginationResponse(klaims);
    }

    @Override
    public List<KlaimListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<Klaim> klaim) {
        List<KlaimListResponseDTO> klaims = klaim.stream().map(k-> {
            KlaimListResponseDTO dto = new KlaimListResponseDTO();
            dto.setKlaimId(k.getKlaimId());
            dto.setKelompokKlaim(k.getKelompokKlaim());
            dto.setKode(k.getKode());
            dto.setNamaKlaim(k.getNamaKlaim());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(klaims);
        paginationResponse.setEmpty(klaim.isEmpty());
        paginationResponse.setFirst(klaim.isFirst());
        paginationResponse.setLast(klaim.isLast());
        paginationResponse.setNumber(klaim.getNumber());
        paginationResponse.setPageable(klaim.getPageable());
        paginationResponse.setSize(klaim.getSize());
        paginationResponse.setSort(klaim.getSort());
        paginationResponse.setTotalElements(klaim.getTotalElements());
        paginationResponse.setNumberOfElements(klaim.getNumberOfElements());
        paginationResponse.setTotalPages(klaim.getTotalPages());
        return paginationResponse;
    }

    @Override
    public KlaimDetailResponseDTO findDetail(Long klaimId) {
        return null;
    }

    @Override
    public void create(KlaimCreateUpdateRequestDTO dto) {
        PendaftaranAsuransi pendaftaranAsuransi = pendaftaranAsuransiRepository.findById(dto.getPendaftaranAsuransiId()).orElseThrow(()->new ResourceNotFoundException("pendaftaran.asuransi.not.found"));
        UsahaTani usahaTani = usahaTaniRepository.findById(dto.getUsahaTaniId()).orElseThrow(()->new ResourceNotFoundException("usaha.tani.not.found"));

        Klaim klaim = new Klaim();
        klaim.setKelompokKlaim(dto.getKelompokKlaim());
        klaim.setTanggunganAsuransi(dto.isTanggunganAsuransi());
        klaim.setTanggunganPetani(dto.isTanggunganPetani());
        klaim.setNamaKlaim(dto.getNamaKlaim());
        klaim.setKode(dto.getKode());
        klaim.setKeterangan(dto.getKeterangan());
        klaim.setCreatedAt(LocalDate.now());
        klaim.setUpdatedAt(LocalDate.now());

        klaim.setPendaftaranAsuransi(pendaftaranAsuransi);
        klaim.setUsahaTani(usahaTani);
        klaimRepository.save(klaim);
    }

    @Override
    public void update(Long klaimId, KlaimCreateUpdateRequestDTO dto) {
        Klaim klaim = klaimRepository.findById(klaimId).orElseThrow(()->new ResourceNotFoundException("Klaim.not.found"));
        klaim.setKelompokKlaim(dto.getKelompokKlaim());
        klaim.setTanggunganAsuransi(dto.isTanggunganAsuransi());
        klaim.setTanggunganPetani(dto.isTanggunganPetani());
        klaim.setNamaKlaim(dto.getNamaKlaim());
        klaim.setKode(dto.getKode());
        klaim.setKeterangan(dto.getKeterangan());
        klaim.setUpdatedAt(LocalDate.now());
        klaimRepository.save(klaim);
    }

    @Override
    public void delete(Long klaimId) {
        klaimRepository.deleteById(klaimId);
    }
}
