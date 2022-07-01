package com.kpb.allocationservice.service;

import com.kpb.allocationservice.dto.PaginationResponse;
import com.kpb.allocationservice.dto.PupukSubsidi.*;

import java.util.List;

public interface PupukSubsidiService {
    public PaginationResponse findAllList(Long petani, int page, int size, Long user);
    public PaginationResponse findAllListPoktan(int page, int size, Long user, Integer tahun);
    public PaginationResponse findAllListbyAllRole(int page, int size, String role, Long user, int tahun);
    public PaginationResponse findAllListBinatani(int page, int size, Long user);
    public PaginationResponse findAllListPenyuluh(int page, int size, Long user);
    public SisaPupukSubsidiResponseDTO findSisaPupuk(Long petani, int tahun, int masa_tanam,String pupuk);
    public InformasiAlokasiPupukSubsidiPoktanResponse alokasiPupukOPDTPH(Long poktanUserId, int tahun);
    public PupukSubsidiDetailResponseDTO findAlokasiDetailByPetaniAndYear(Long petani, Long userId, int tahun, int masa_tanam);
    public List<PupukSubsidiDetailResponseDTO> findAlokasiDetailByPoktanAndYear(Long userId, int tahun, int masa_tanam);
    public PupukSubsidiDetailResponseDTO findDetail(Long pupukSubsidiId);
    public void create(PupukSubsidiCreateUpdateRequestDTO dto);
    public void update(Long pupukSubsidiId, PupukSubsidiCreateUpdateRequestDTO dto);
    public void delete(Long pupukSubsidiId);
}
