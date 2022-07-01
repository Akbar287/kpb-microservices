package com.kpb.insuranceservice.service.Impl;

import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.PetaniResponse;
import com.kpb.insuranceservice.domain.PendaftaranAsuransi;
import com.kpb.insuranceservice.domain.UsahaTani;
import com.kpb.insuranceservice.dto.PaginationResponse;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniCreateUpdateRequestDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniDetailResponseDTO;
import com.kpb.insuranceservice.dto.UsahaTani.UsahaTaniListResponseDTO;
import com.kpb.insuranceservice.exception.ResourceNotFoundException;
import com.kpb.insuranceservice.repository.PendaftaranAsuransiRepository;
import com.kpb.insuranceservice.repository.UsahaTaniRepository;
import com.kpb.insuranceservice.service.UsahaTaniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UsahaTaniServiceImpl implements UsahaTaniService {

    @Autowired
    private UsahaTaniRepository usahaTaniRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private PendaftaranAsuransiRepository pendaftaranAsuransiRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "usahaTaniId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        List<Long> listPetaniId = memberClient.findListPetaniBySearchNik(search);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<UsahaTani> usahaTanis;
        if(search.equals("")) {
            usahaTanis = usahaTaniRepository.findAll(pageable);
        } else {
            usahaTanis = usahaTaniRepository.findAllByPetaniIdIn(listPetaniId, pageable);
        }

        return this.paginationResponse(usahaTanis);
    }

    @Override
    public List<UsahaTaniListResponseDTO> findAllList() {
        return null;
    }

    public PaginationResponse paginationResponse(Page<UsahaTani> usahaTani) {
        List<UsahaTaniListResponseDTO> usahaTanis = usahaTani.stream().map(ut-> {

            PetaniResponse petaniResponse = memberClient.findPetaniByUserId(ut.getPetaniId());
            CreateUserResponse userResponse = userCreateClient.getUser(petaniResponse.getUserId());

            UsahaTaniListResponseDTO dto = new UsahaTaniListResponseDTO();
            dto.setUsahaTaniId(ut.getUsahaTaniId());
            dto.setUsername(userResponse.getUsername());
            dto.setName(userResponse.getNama());
            dto.setAktif(ut.isAktif());
            dto.setMasaTanam(ut.getMasaTanam());
            dto.setTahun(ut.getTahun());
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(usahaTanis);
        paginationResponse.setEmpty(usahaTani.isEmpty());
        paginationResponse.setFirst(usahaTani.isFirst());
        paginationResponse.setLast(usahaTani.isLast());
        paginationResponse.setNumber(usahaTani.getNumber());
        paginationResponse.setPageable(usahaTani.getPageable());
        paginationResponse.setSize(usahaTani.getSize());
        paginationResponse.setSort(usahaTani.getSort());
        paginationResponse.setTotalElements(usahaTani.getTotalElements());
        paginationResponse.setNumberOfElements(usahaTani.getNumberOfElements());
        paginationResponse.setTotalPages(usahaTani.getTotalPages());
        return paginationResponse;
    }

    @Override
    public UsahaTaniDetailResponseDTO findDetail(Long usahaTaniId) {
        UsahaTani usahaTani = usahaTaniRepository.findById(usahaTaniId).orElseThrow(()->new ResourceNotFoundException("usaha.tani.not.found"));

        PetaniResponse petaniResponse = memberClient.findPetani(usahaTani.getPetaniId());
        CreateUserResponse userResponse = userCreateClient.getUser(petaniResponse.getUserId());

        UsahaTaniDetailResponseDTO dto = new UsahaTaniDetailResponseDTO();
        dto.setUsahaTaniId(usahaTani.getUsahaTaniId());
        dto.setTahun(usahaTani.getTahun());
        dto.setMasaTanam(usahaTani.getMasaTanam());
        dto.setLuasArea(usahaTani.getLuasArea());
        dto.setNamaKomoditas(usahaTani.getNamaKomoditas());
        dto.setSubsektor(usahaTani.getSubsektor());
        dto.setAktif(usahaTani.isAktif());
        dto.setPendaftaranAsuransi(usahaTani.getPendaftaranAsuransi());
        dto.setUser(userResponse);
        return dto;
    }

    @Override
    public void create(UsahaTaniCreateUpdateRequestDTO dto) {
        PendaftaranAsuransi pendaftaranAsuransi = pendaftaranAsuransiRepository.findById(dto.getPendaftaranAsuransiId()).orElseThrow(()->new ResourceNotFoundException("pendaftaran.asuransi.not.found"));

        UsahaTani usahaTani = new UsahaTani();
        usahaTani.setPetaniId(dto.getPetaniId());
        usahaTani.setPendaftaranAsuransi(pendaftaranAsuransi);
        usahaTani.setTahun(dto.getTahun());
        usahaTani.setMasaTanam(dto.getMasaTanam());
        usahaTani.setLuasArea(dto.getLuasArea());
        usahaTani.setNamaKomoditas(dto.getNamaKomoditas());
        usahaTani.setSubsektor(dto.getSubsektor());
        usahaTani.setAktif(dto.isAktif());
        usahaTaniRepository.save(usahaTani);
    }

    @Override
    public void update(Long usahaTaniId, UsahaTaniCreateUpdateRequestDTO dto) {
        UsahaTani usahaTani = usahaTaniRepository.findById(usahaTaniId).orElseThrow(()->new ResourceNotFoundException("usaha.tani.not.found"));
        usahaTani.setTahun(dto.getTahun());
        usahaTani.setMasaTanam(dto.getMasaTanam());
        usahaTani.setLuasArea(dto.getLuasArea());
        usahaTani.setNamaKomoditas(dto.getNamaKomoditas());
        usahaTani.setSubsektor(dto.getSubsektor());
        usahaTani.setAktif(dto.isAktif());
        usahaTaniRepository.save(usahaTani);
    }

    @Override
    public void delete(Long usahaTaniId) {
        usahaTaniRepository.deleteById(usahaTaniId);
    }
}

