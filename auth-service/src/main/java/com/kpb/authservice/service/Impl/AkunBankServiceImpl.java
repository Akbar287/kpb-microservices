package com.kpb.authservice.service.Impl;

import com.kpb.authservice.domain.BankAccount;
import com.kpb.authservice.domain.User;
import com.kpb.authservice.dto.akun.AkunBankDetailResponseDTO;
import com.kpb.authservice.dto.akun.AkunBankRequestDTO;
import com.kpb.authservice.exception.ResourceNotFoundException;
import com.kpb.authservice.repository.AkunBankRepository;
import com.kpb.authservice.repository.UserRepository;
import com.kpb.authservice.service.AkunBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AkunBankServiceImpl implements AkunBankService {

    @Autowired
    private AkunBankRepository akunBankRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AkunBankDetailResponseDTO> findAkun(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user.not.found"));

        return user.getBankAccounts().stream().map(akun->{
            AkunBankDetailResponseDTO dto = new AkunBankDetailResponseDTO();
            dto.setAkunBankId(akun.getAkunBankId());
            dto.setKodeBank(akun.getKodeBank());
            dto.setNomorRekening(akun.getNomorRekening());
            dto.setNamaBank(akun.getNamaBank());
            return dto;
        }).collect(Collectors.toList());
    }

    public AkunBankDetailResponseDTO akunBankDetailResponseDTO(Long akunId) {
        BankAccount akun = akunBankRepository.findById(akunId).orElseThrow(()->new ResourceNotFoundException("akun.not.found"));
        AkunBankDetailResponseDTO dto = new AkunBankDetailResponseDTO();
        dto.setAkunBankId(akun.getAkunBankId());
        dto.setKodeBank(akun.getKodeBank());
        dto.setNomorRekening(akun.getNomorRekening());
        dto.setNamaBank(akun.getNamaBank());
        return dto;
    }

    @Override
    public AkunBankDetailResponseDTO createAkun(Long userId, AkunBankRequestDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user.not.found"));

        BankAccount bankAccount = new BankAccount();
        bankAccount.setKodeBank(dto.getKodeBank());
        bankAccount.setNamaBank(dto.getNamaBank());
        bankAccount.setNomorRekening(dto.getNomorRekening());
        bankAccount.setCreatedAt(LocalDate.now());
        bankAccount.setUpdatedAt(LocalDate.now());
        bankAccount.setUser(user);

        akunBankRepository.save(bankAccount);
        return this.akunBankDetailResponseDTO(bankAccount.getAkunBankId());
    }

    @Override
    public void updateAkun(Long akunBankId, AkunBankRequestDTO dto) {
        BankAccount akun = akunBankRepository.findById(akunBankId).orElseThrow(()->new ResourceNotFoundException("akun.not.found"));

        akun.setNamaBank(dto.getNamaBank());
        akun.setKodeBank(dto.getKodeBank());
        akun.setNomorRekening(dto.getNomorRekening());
        akun.setUpdatedAt(LocalDate.now());
        akunBankRepository.save(akun);
    }

    @Override
    public void deleteAkun(Long akunBankId) {
        akunBankRepository.deleteById(akunBankId);
    }
}
