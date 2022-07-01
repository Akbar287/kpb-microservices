package com.kpb.transactionalservice.service.Impl;

import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.transactionalservice.domain.Produk;
import com.kpb.transactionalservice.domain.Stok;
import com.kpb.transactionalservice.dto.Stok.StokRequestDTO;
import com.kpb.transactionalservice.dto.Stok.StokResponseDTO;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.ProdukRepository;
import com.kpb.transactionalservice.repository.StokRepository;
import com.kpb.transactionalservice.service.StokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StokServiceImpl implements StokService {

    @Autowired
    private StokRepository stokRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private MemberClient memberClient;

    @Override
    public StokResponseDTO findDetail(Long produkId, Long distributor, Long kios, int tahun, String bulan) {
        Stok stok = stokRepository.findAllByDistributorIdAndKiosIdAndProduk_ProdukIdAndBulanAndTahun(distributor, kios, produkId, bulan, tahun);
        StokResponseDTO dto = new StokResponseDTO();
        if(stok == null) {
            return dto;
        } else {
            DistributorResponse distributorResponse = memberClient.findDistributor(stok.getDistributorId());
            KiosResponse kiosResponse = memberClient.findKios(stok.getKiosId());
            dto.setStokId(stok.getStokKiosId());
            dto.setBulan(stok.getBulan());
            dto.setTahun(stok.getTahun());
            dto.setStokAwal(stok.getStokAwal());
            dto.setStokSubsidi(stok.getStokSubsidi());
            dto.setStokPenyaluran(stok.getStokPenyaluran());
            dto.setStokAkhir(stok.getStokAkhir());
            dto.setDistributor(distributorResponse);
            dto.setKios(kiosResponse);
            return dto;
        }
    }

    @Override
    public void create(StokRequestDTO dto) {
        try {
            KiosResponse kiosResponse = memberClient.findKios(dto.getKiosId());
            DistributorResponse distributorResponse = memberClient.findDistributor(dto.getDistributorId());
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("kios.or.distributor.not.found");
        }
        Produk produk = produkRepository.findById(dto.getProdukId()).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));
        Stok stokCheck = stokRepository.findByDistributorIdAndBulanAndTahunAndKiosIdAndProduk_ProdukId(dto.getDistributorId(), dto.getBulan(), dto.getTahun(), dto.getKiosId(), dto.getProdukId());
        if(stokCheck == null) {
            Stok stok = new Stok();
            stok.setKiosId(dto.getKiosId());
            stok.setDistributorId(dto.getDistributorId());
            stok.setTahun(dto.getTahun());
            stok.setBulan(dto.getBulan());
            stok.setProduk(produk);
            stok.setStokAwal(dto.getStokAwal());
            stok.setStokSubsidi(dto.getStokSubsidi());
            stok.setStokPenyaluran(dto.getStokPenyaluran());
            stok.setStokAkhir(dto.getStokAkhir());
            stokRepository.save(stok);
        } else {
            throw new ResourceNotFoundException("stok.already.created.before");
        }
    }

    @Override
    public void update(StokRequestDTO dto) {
        try {
            KiosResponse kiosResponse = memberClient.findKios(dto.getKiosId());
            DistributorResponse distributorResponse = memberClient.findDistributor(dto.getDistributorId());
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("kios.or.distributor.not.found");
        }

        Stok stok = stokRepository.findAllByDistributorIdAndKiosIdAndProduk_ProdukIdAndBulanAndTahun(dto.getDistributorId(), dto.getKiosId(), dto.getProdukId(), dto.getBulan(), dto.getTahun());
        if(stok != null) {
            stok.setKiosId(dto.getKiosId());
            stok.setDistributorId(dto.getDistributorId());
            stok.setStokAwal(dto.getStokAwal());
            stok.setStokSubsidi(dto.getStokSubsidi());
            stok.setStokPenyaluran(dto.getStokPenyaluran());
            stok.setStokAkhir(dto.getStokAkhir());
            stokRepository.save(stok);
        }
    }

    @Override
    public void delete(Long stokId) {
        Stok stok = stokRepository.findById(stokId).orElseThrow(()->new ResourceNotFoundException("stok.not.found"));
        stokRepository.delete(stok);
    }
}
