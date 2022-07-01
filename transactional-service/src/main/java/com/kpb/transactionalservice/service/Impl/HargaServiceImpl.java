package com.kpb.transactionalservice.service.Impl;

import com.kpb.transactionalservice.domain.Harga;
import com.kpb.transactionalservice.domain.Produk;
import com.kpb.transactionalservice.dto.Harga.HargaRequestDTO;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.HargaRepository;
import com.kpb.transactionalservice.repository.ProdukRepository;
import com.kpb.transactionalservice.service.HargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HargaServiceImpl implements HargaService {

    @Autowired
    private HargaRepository hargaRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public HargaResponseDTO findDetail(Long produkId) {
        Produk produk = produkRepository.findById(produkId).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));

        HargaResponseDTO hargaResponseDTO = new HargaResponseDTO();
        hargaResponseDTO.setHargaId(produk.getHarga() != null ? produk.getHarga().getHargaId() : 0);
        hargaResponseDTO.setHarga(produk.getHarga() != null ? produk.getHarga().getHarga().intValue() : 0);
        hargaResponseDTO.setHargaSubsidi(produk.getHarga() != null ? produk.getHarga().getHargaSubsidi().intValue() : 0);
        hargaResponseDTO.setHargaKios(produk.getHarga() != null ? produk.getHarga().getHargaKios().intValue() : 0);
        hargaResponseDTO.setHargaKiosSubsidi(produk.getHarga() != null ? produk.getHarga().getHargaKiosSubsidi().intValue() : 0);
        hargaResponseDTO.setBiayaKios(produk.getHarga() != null ? produk.getHarga().getBiayaKios().intValue() : 0);
        hargaResponseDTO.setBiayaKiosSubsidi(produk.getHarga() != null ? produk.getHarga().getBiayaKiosSubsidi().intValue() : 0);
        hargaResponseDTO.setBiayaLain(produk.getHarga() != null ? produk.getHarga().getBiayaLain().intValue() : 0);
        return hargaResponseDTO;
    }

    @Override
    public void create(HargaRequestDTO dto) {
        Produk produk = produkRepository.findById(dto.getProdukId()).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));

        if(produk.getHarga() == null) {
            Harga harga = new Harga();
            harga.setHarga(dto.getHarga());
            harga.setHargaSubsidi(dto.getHargaSubsidi());
            harga.setHargaKios(dto.getHargaKios());
            harga.setHargaKiosSubsidi(dto.getHargaKiosSubsidi());
            harga.setBiayaKios(dto.getBiayaKios());
            harga.setBiayaKiosSubsidi(dto.getBiayaKiosSubsidi());
            harga.setBiayaLain(dto.getBiayaLain());
            harga.setProduk(produk);
            hargaRepository.save(harga);
        } else {
            throw new ResourceNotFoundException("harga.already.created.before");
        }
    }

    @Override
    public void update(Long hargaId, HargaRequestDTO dto) {
        Harga harga = hargaRepository.findById(hargaId).orElseThrow(()->new ResourceNotFoundException("harga.not.found"));
        harga.setHarga(dto.getHarga());
        harga.setHargaSubsidi(dto.getHargaSubsidi());
        harga.setHargaKios(dto.getHargaKios());
        harga.setHargaKiosSubsidi(dto.getHargaKiosSubsidi());
        harga.setBiayaKios(dto.getBiayaKios());
        harga.setBiayaKiosSubsidi(dto.getBiayaKiosSubsidi());
        harga.setBiayaLain(dto.getBiayaLain());
        hargaRepository.save(harga);
    }

    @Override
    public void delete(Long hargaId) {
        Harga harga = hargaRepository.findById(hargaId).orElseThrow(()->new ResourceNotFoundException("harga.not.found"));
        hargaRepository.delete(harga);
    }
}
