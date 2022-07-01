package com.kpb.authservice.service;

import com.kpb.authservice.dto.akun.AkunBankDetailResponseDTO;
import com.kpb.authservice.dto.akun.AkunBankRequestDTO;

import java.util.List;

public interface AkunBankService {
    public List<AkunBankDetailResponseDTO> findAkun (Long userId);
    public AkunBankDetailResponseDTO createAkun (Long userId, AkunBankRequestDTO dto);
    public void updateAkun (Long akunBankId, AkunBankRequestDTO dto);
    public void deleteAkun (Long akunBankId);
}
