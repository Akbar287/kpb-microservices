package com.kpb.transactionalservice.service.Impl;

import com.kpb.clientservice.feign.AlokasiClient;
import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.feign.UserCreateClient;
import com.kpb.clientservice.web.Alokasi.AlokasiPupukCreateUpdateRequest;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.*;
import com.kpb.transactionalservice.domain.*;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.PenebusanPupuk.PenebusanPupukResponseDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiCreateRequestDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiDetailResponseDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiListResponseDTO;
import com.kpb.transactionalservice.dto.Transaksi.TransaksiUpdateRequestDTO;
import com.kpb.transactionalservice.dto.TransaksiDetail.TransaksiDetailListResponseDTO;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.*;
import com.kpb.transactionalservice.service.TransaksiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service @Slf4j
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private TransaksiDetailRepository transaksiDetailRepository;

    @Autowired
    private PenebusanPupukRepository penebusanPupukRepository;

    @Autowired
    private PenebusanPupukStatusRepository penebusanPupukStatusRepository;

    @Autowired
    private AlokasiClient alokasiClient;

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private StokRepository stokRepository;

    @Autowired
    private UserCreateClient userCreateClient;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search, Long userId, String jenis, Long kiosUserId) {
        sort = (sort != null) ? sort : (Objects.equals(jenis, "penebusan")) ? "transaksi_id" : "transaksiId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<Transaksi> transaksi;

        if(!Objects.isNull(kiosUserId)) {
                KiosResponse kiosResponse = memberClient.findKiosByUser(kiosUserId);
                transaksi = transaksiRepository.findAllByNamaTransaksiLikeAndKiosId(search, kiosResponse.getKiosId(), pageable);
        } else {
            if (Objects.isNull(userId)) {
                if(jenis.equals("penebusan")) {
                    transaksi = transaksiRepository.findAllByNamaTransaksiLikeAndPenebusanIsTrue(search, pageable);
                } else {
                    transaksi = transaksiRepository.findAllByNamaTransaksiLike(search, pageable);
                }
            } else {
                PetaniResponse petaniResponse = memberClient.findPetaniByUserId(userId);

                if(jenis.equals("penebusan")) {
                    transaksi =transaksiRepository.findAllByNamaTransaksiLikeAndPetaniIdAndPenebusanIsTrue(search, petaniResponse.getPetaniId(), pageable);
                } else {
                    transaksi =transaksiRepository.findAllByNamaTransaksiLikeAndPetaniId(search, petaniResponse.getPetaniId(), pageable);
                }
            }
        }
        return this.paginationResponse(transaksi);
    }

    @Override
    public PaginationResponse findAllToPoktan(int page, int size, String sort, String search, Long poktanUserId, String jenis) {
        sort = (sort != null) ? sort : (Objects.equals(jenis, "penebusan")) ? "transaksi_id" : "transaksi_id";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<Transaksi> transaksi;
        jenis.toLowerCase();

        PoktanResponse poktanResponse = memberClient.findPoktanByUserId(poktanUserId);

        log.info(String.valueOf(poktanResponse.getPoktanId()));

        if(jenis.equals(("penebusan")) || jenis.equals("pembelian")) {
            transaksi = transaksiRepository.findForPoktan(search, poktanResponse.getPoktanId(), jenis.equals("penebusan"), pageable);
        } else {
            transaksi = transaksiRepository.findForPoktanNotCondition(search, poktanResponse.getPoktanId(), pageable);
        }
        return this.paginationResponse(transaksi);
    }

    @Override
    public PaginationResponse findAllToAllRole(int page, int size, String sort, String search, Long userId, String jenis, String role) {
        sort = "createdAt";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        Page<Transaksi> transaksi = null;
        jenis.toLowerCase();

        List<Long> listKios = null;
        if(role.equals("distributor")) {
             listKios = memberClient.findListKiosIdByDistributorUserId(userId);
        } else if (role.equals("pabrikan")) {
             listKios = memberClient.findListKiosByPabrikanUserId(userId);
        }

        if(listKios == null) {
            transaksi = transaksiRepository.findAllByNamaTransaksiLike(search, pageable);
        } else {
            transaksi = (jenis.equals("all")) ? transaksiRepository.findAllByNamaTransaksiLikeAndKiosIdIn(search, listKios, pageable) : transaksiRepository.findAllByNamaTransaksiLikeAndKiosIdInAndPenebusanTrue(search, listKios, jenis.equals("penebusan"), pageable);
        }

        return this.paginationResponse(transaksi);
    }

    public PaginationResponse paginationResponse (Page<Transaksi> transaksi) {
        List<TransaksiListResponseDTO> transaksiListResponseDTOList = transaksi.stream().map(b->{
            PenebusanPupukStatus status = b.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(x->x.getLabel().equals("Dibatalkan")).findFirst().orElse(b.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(y->y.getLabel().equals("Diambil")).findFirst().orElse(b.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(y->y.getLabel().equals("Dibayar")).findFirst().orElse(b.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(y->y.getLabel().equals("Dikonfirmasi")).findFirst().orElse(b.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(y->y.getLabel().equals("Dibuat")).findFirst().orElse(null)))));

            String st = (status != null) ? "" : status.getLabel();

            TransaksiListResponseDTO dto = new TransaksiListResponseDTO();
            dto.setTransaksiId(b.getTransaksiId());
            dto.setStatus(st);
            dto.setNamaTransaksi(b.getNamaTransaksi());
            dto.setTransferKios(b.isTransferKios());
            dto.setPenebusan(b.isPenebusan());
            dto.setGrandTotal(b.getGrandTotal().intValue());
            dto.setCreatedAt(String.valueOf(b.getCreatedAt()));
            dto.setUpdatedAt(String.valueOf(b.getUpdatedAt()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(transaksiListResponseDTOList);
        paginationResponse.setEmpty(transaksi.isEmpty());
        paginationResponse.setFirst(transaksi.isFirst());
        paginationResponse.setLast(transaksi.isLast());
        paginationResponse.setNumber(transaksi.getNumber());
        paginationResponse.setPageable(transaksi.getPageable());
        paginationResponse.setSize(transaksi.getSize());
        paginationResponse.setSort(transaksi.getSort());
        paginationResponse.setTotalElements(transaksi.getTotalElements());
        paginationResponse.setNumberOfElements(transaksi.getNumberOfElements());
        paginationResponse.setTotalPages(transaksi.getTotalPages());
        return paginationResponse;
    }

    @Override
    public TransaksiDetailResponseDTO findDetail(Long transaksiId) {
        Transaksi transaksi =transaksiRepository.findById(transaksiId).orElseThrow(()->new ResourceNotFoundException("transaksi.not.found"));

        PetaniResponse petaniResponse = null;
        PoktanResponse poktanResponse = null;
        if(transaksi.getPenebusanPupuk().isPetani()) {
            petaniResponse = memberClient.findPetani(transaksi.getPetaniId());
        } else if(transaksi.getPenebusanPupuk().isPoktan()) {
            poktanResponse = memberClient.findPoktanByPoktanId(transaksi.getPetaniId());
        }

        CreateUserResponse userResponse = userCreateClient.getUser((transaksi.getPenebusanPupuk().isPetani()) ? petaniResponse.getUserId() : poktanResponse.getUserId());

        PenebusanPupukResponseDTO penebusan = new PenebusanPupukResponseDTO();
        penebusan.setPenebusanPupukId(transaksi.getPenebusanPupuk().getPenebusanPupukId());
        penebusan.setPetani(transaksi.getPenebusanPupuk().isPetani());
        penebusan.setPoktan(transaksi.getPenebusanPupuk().isPoktan());

        List<TransaksiDetailListResponseDTO> transaksiDetailListResponseDTOS = transaksi.getTransaksiDetail().stream().map(b->{
            ProdukResponseDTO produkResponseDTO = new ProdukResponseDTO();
            produkResponseDTO.setProdukId(b.getProduk().getProdukId());
            produkResponseDTO.setNamaProduk(b.getProduk().getNamaProduk());
            produkResponseDTO.setFileGambar(b.getProduk().getFileGambar());
            produkResponseDTO.setSatuan(b.getProduk().getSatuan());

            TransaksiDetailListResponseDTO dto = new TransaksiDetailListResponseDTO();
            dto.setTransaksiDetailId(b.getTransaksiDetailId());
            dto.setTotal(b.getTotal().intValue());
            dto.setHarga(b.getHarga().intValue());
            dto.setJumlah(b.getJumlah());
            dto.setSubsidi(b.isSubsidi());
            dto.setProduk(produkResponseDTO);
            return dto;
        }).toList();

        List<PenebusanPupukStatus> penebusanPupukStatuses = transaksi.getPenebusanPupuk().getPenebusanPupukStatus().stream().map(b->{
            PenebusanPupukStatus penebusanPupukStatus = new PenebusanPupukStatus();
            penebusanPupukStatus.setPenebusanPupukStatusId(b.getPenebusanPupukStatusId());
            penebusanPupukStatus.setPesan(b.getPesan());
            penebusanPupukStatus.setLabel(b.getLabel());
            penebusanPupukStatus.setCreatedAt(b.getCreatedAt());
            return penebusanPupukStatus;
        }).toList();

        KiosResponse kiosResponse = null;
        if(transaksi.getKiosId() != null) {
            kiosResponse = memberClient.findKios(transaksi.getKiosId());
        } else {
            kiosResponse = new KiosResponse();
        }

        TransaksiDetailResponseDTO transaksiDetail = new TransaksiDetailResponseDTO();
        transaksiDetail.setTransaksiId(transaksi.getTransaksiId());
        transaksiDetail.setNamaTransaksi(transaksi.getNamaTransaksi());
        transaksiDetail.setGrandTotal(transaksi.getGrandTotal().intValue());
        transaksiDetail.setMetodePembayaran(transaksi.getMetodePembayaran());
        transaksiDetail.setVirtualAccount(transaksi.getVirtualAccount());
        transaksiDetail.setStatusKredit(transaksi.isStatusKredit());
        transaksiDetail.setVerifikasiKredit(transaksi.isVerifikasiKredit());
        transaksiDetail.setStatusRegistrasiKredit(transaksi.getStatusRegistrasiKredit().intValue());
        transaksiDetail.setTransferKios(transaksi.isTransferKios());
        transaksiDetail.setPenebusan(transaksi.isPenebusan());
        transaksiDetail.setPenebusanPupukStatus(penebusanPupukStatuses);
        transaksiDetail.setPenebusanPupuk(penebusan);
        transaksiDetail.setUser(userResponse);
        transaksiDetail.setKios(kiosResponse);
        transaksiDetail.setTransaksiDetail(transaksiDetailListResponseDTOS);
        return transaksiDetail;
    }

    @Override
    public void create(TransaksiCreateRequestDTO dto) {
        PetaniResponse petaniResponse = null;
        PoktanResponse poktanResponse = null;
        KiosResponse kiosResponse = null;
        DistributorResponse distributorResponse = null;
        if(dto.getPenebusanPupuk().isPetani()) {
            petaniResponse = memberClient.findPetaniByUserId(dto.getUserId());
            kiosResponse = memberClient.findKiosByPetaniUser(petaniResponse.getUserId());
            distributorResponse = memberClient.findDistributorByPetaniFromUser(petaniResponse.getUserId());
        } else {
            poktanResponse = memberClient.findPoktanByUserId(dto.getUserId());
            kiosResponse = memberClient.findKiosByPoktanUser(poktanResponse.getUserId());
            distributorResponse = memberClient.findDistributorByPoktanFromUser(poktanResponse.getUserId());
        }

        //Penebusan
        PenebusanPupuk penebusanPupuk = new PenebusanPupuk();
        penebusanPupuk.setPetani(dto.getPenebusanPupuk().isPetani());
        penebusanPupuk.setPoktan(dto.getPenebusanPupuk().isPoktan());
        penebusanPupukRepository.save(penebusanPupuk);

        //Penebusan Status
        String pesan = (dto.isPenebusan()) ? "Transaksi Penebusan Pupuk Sudah dibuat." : "Transaksi Pembelian Pupuk Sudah dibuat.";
        PenebusanPupukStatus penebusanPupukStatus = new PenebusanPupukStatus();
        penebusanPupukStatus.setLabel("Dibuat");
        penebusanPupukStatus.setPesan(pesan);
        penebusanPupukStatus.setPenebusanPupuk(penebusanPupuk);
        penebusanPupukStatus.setCreatedAt(LocalDate.now());
        penebusanPupukStatusRepository.save(penebusanPupukStatus);

        //Transaksi
        Transaksi transaksi = new Transaksi();
        transaksi.setNamaTransaksi(dto.getNamaTransaksi());
        transaksi.setGrandTotal(BigInteger.valueOf(dto.getGrandTotal()));
        transaksi.setMetodePembayaran(dto.getMetodePembayaran());
        transaksi.setVirtualAccount("00000000000000000000");
        transaksi.setTransferKios(dto.isTransferKios());
        transaksi.setStatusKredit(dto.isStatusKredit());
        transaksi.setKiosId(dto.getKiosId());
        transaksi.setVerifikasiKredit(dto.isStatusKredit());
        transaksi.setStatusRegistrasiKredit(BigInteger.valueOf(0));
        transaksi.setCreatedAt(LocalDate.now());
        transaksi.setUpdatedAt(LocalDate.now());
        transaksi.setPetaniId((dto.getPenebusanPupuk().isPetani()) ? petaniResponse.getPetaniId() : poktanResponse.getPoktanId());
        transaksi.setPenebusan(dto.isPenebusan());
        transaksi.setPenebusanPupuk(penebusanPupuk);
        transaksiRepository.save(transaksi);

        //TransaksiDetail
        PetaniResponse finalPetaniResponse = petaniResponse;
        KiosResponse finalKiosResponse = kiosResponse;
        DistributorResponse finalDistributorResponse = distributorResponse;
        dto.getTransaksi().forEach(b-> {
            Produk produk = produkRepository.findById(b.getProdukId()).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));
            TransaksiDetail transaksiDetail = new TransaksiDetail();
            transaksiDetail.setHarga(BigInteger.valueOf(b.getHarga()));
            transaksiDetail.setJenis(b.getJenis());
            transaksiDetail.setJumlah(b.getJumlah());
            transaksiDetail.setKomoditas(b.getKomoditas());
            transaksiDetail.setSubsidi(b.isSubsidi());
            transaksiDetail.setTotal(BigInteger.valueOf(b.getTotal()));
            transaksiDetail.setTahun(b.getTahun());
            transaksiDetail.setMasaTanam(b.getMasaTanam());
            transaksiDetail.setProduk(produk);
            transaksiDetail.setTransaksi(transaksi);
            transaksiDetailRepository.save(transaksiDetail);

            if(!dto.isPenebusan()) {
            Stok stok = produk.getStok().stream().filter(x-> x.getKiosId().equals(finalKiosResponse.getKiosId()) && x.getDistributorId().equals(finalDistributorResponse.getDistributorId()) && x.getTahun() == b.getTahun() && x.getBulan().equals(parseMonth(LocalDate.now().getMonthValue()))).findFirst().orElse(null);
                if (stok != null) {
                    stok.setStokAwal(stok.getStokAwal() - b.getJumlah());
                    stok.setStokAkhir(stok.getStokAkhir() + b.getJumlah());
                    stokRepository.save(stok);
                }
            }

            if (dto.isPenebusan()) {
                AlokasiPupukCreateUpdateRequest alokasiPupukCreateUpdateRequest = new AlokasiPupukCreateUpdateRequest();
                alokasiPupukCreateUpdateRequest.setTahun(b.getTahun());
                alokasiPupukCreateUpdateRequest.setMasaTanam(b.getMasaTanam());
                alokasiPupukCreateUpdateRequest.setNamaPupuk(produk.getNamaProduk());
                alokasiPupukCreateUpdateRequest.setJumlah(b.getJumlah());
                alokasiPupukCreateUpdateRequest.setPetaniId((dto.getPenebusanPupuk().isPetani()) ? Objects.requireNonNull(finalPetaniResponse).getPetaniId() : b.getPetaniId());

                alokasiPupukCreateUpdateRequest.setTransaksiId(transaksiDetail.getTransaksiDetailId());
                alokasiClient.createAllocation(alokasiPupukCreateUpdateRequest);
            }
        });
    }

    @Override
    public void update(Long transaksiId, TransaksiUpdateRequestDTO dto) {
        Transaksi transaksi = transaksiRepository.findById(transaksiId).orElseThrow(()->new ResourceNotFoundException("transaksi.not.found"));

        PenebusanPupukStatus penebusanPupukStatus = new PenebusanPupukStatus();

        switch (dto.getStatus()) {
            case "KONFIRMASI" -> {
                if (transaksi.isPenebusan()) {
                    PetaniResponse petaniResponse = null;
                    PoktanResponse poktanResponse = null;
                    KiosResponse kiosResponse = null;
                    DistributorResponse distributorResponse = null;

                    if(transaksi.getPenebusanPupuk().isPetani()) {
                        petaniResponse = memberClient.findPetani(transaksi.getPetaniId());
                        kiosResponse = memberClient.findKiosByPetaniUser(petaniResponse.getUserId());
                        distributorResponse = memberClient.findDistributorByPetaniFromUser(petaniResponse.getUserId());
                    } else {
                        poktanResponse = memberClient.findPoktanByUserId(transaksi.getPetaniId());
                        kiosResponse = memberClient.findKiosByPoktanUser(poktanResponse.getUserId());
                        distributorResponse = memberClient.findDistributorByPoktanFromUser(poktanResponse.getUserId());
                    }

                    KiosResponse finalKiosResponse = kiosResponse;
                    DistributorResponse finalDistributorResponse = distributorResponse;
                    transaksi.getTransaksiDetail().forEach(a->{
                        Stok stok = a.getProduk().getStok().stream().filter(x-> x.getKiosId().equals(finalKiosResponse.getKiosId()) && x.getDistributorId().equals(finalDistributorResponse.getDistributorId()) && x.getTahun() == transaksi.getCreatedAt().getYear() && x.getBulan().equals(parseMonth(transaksi.getCreatedAt().getMonthValue()))).findFirst().orElse(null);

                        if (stok != null) {
                            stok.setStokSubsidi(stok.getStokSubsidi() - a.getJumlah());
                            stok.setStokPenyaluran(stok.getStokPenyaluran() + a.getJumlah());
                            stokRepository.save(stok);
                        }
                    });
                }
                penebusanPupukStatus.setLabel("Dikonfirmasi");
                penebusanPupukStatus.setPesan("Transaksi dikonfirmasi oleh kios");
            }
            case "DIBAYAR" -> {
                transaksi.setTransferKios(true);
                penebusanPupukStatus.setLabel("Dibayar");
                penebusanPupukStatus.setPesan("Transaksi telah dibayar oleh pembeli");
            }
            case "DIAMBIL" -> {
                penebusanPupukStatus.setLabel("Diambil");
                penebusanPupukStatus.setPesan("Produk telah diambil oleh pembeli");
            }
        }

        penebusanPupukStatus.setPenebusanPupuk(transaksi.getPenebusanPupuk());
        penebusanPupukStatus.setCreatedAt(LocalDate.now());
        penebusanPupukStatusRepository.save(penebusanPupukStatus);
        transaksiRepository.save(transaksi);
    }

    @Override
    public void delete(Long transaksiId) {
        Transaksi transaksi = transaksiRepository.findById(transaksiId).orElseThrow(()->new ResourceNotFoundException("transaksi.not.found"));

        PenebusanPupukStatus status = transaksi.getPenebusanPupuk().getPenebusanPupukStatus().stream().filter(x->x.getLabel().equals("Dibuat")).findFirst().orElse(null);
        String bulan = parseMonth((status == null) ? LocalDate.now().getMonthValue() : status.getCreatedAt().getMonthValue());

        transaksi.getTransaksiDetail().forEach(b->{
            Stok stok = b.getProduk().getStok().stream().filter(x->x.getTahun() == b.getTahun() && x.getBulan().equals(bulan)).findFirst().orElse(null);

            if(transaksi.isPenebusan()) {
                AlokasiPupukCreateUpdateRequest alokasiPupukCreateUpdateRequest = new AlokasiPupukCreateUpdateRequest();
                alokasiPupukCreateUpdateRequest.setPetaniId(transaksi.getPetaniId());
                alokasiPupukCreateUpdateRequest.setMasaTanam(b.getMasaTanam());
                alokasiPupukCreateUpdateRequest.setTahun(b.getTahun());
                alokasiPupukCreateUpdateRequest.setNamaPupuk(b.getProduk().getNamaProduk());
                alokasiPupukCreateUpdateRequest.setJumlah(b.getJumlah());
                alokasiClient.deleteAllocation(alokasiPupukCreateUpdateRequest);

                if(stok != null) stok.setStokSubsidi(stok.getStokSubsidi() + b.getJumlah());
                if(stok != null) stok.setStokPenyaluran(stok.getStokPenyaluran() - b.getJumlah());
            } else {
                if(stok != null) stok.setStokAwal(stok.getStokAwal() + b.getJumlah());
                if(stok != null) stok.setStokAkhir(stok.getStokAkhir() - b.getJumlah());
            }
            if(stok != null) stokRepository.save(stok);
        });

        PenebusanPupukStatus penebusanPupukStatus = new PenebusanPupukStatus();
        penebusanPupukStatus.setLabel("Dibatalkan");
        penebusanPupukStatus.setPesan("Transaksi Dibatalkan");
        penebusanPupukStatus.setPenebusanPupuk(transaksi.getPenebusanPupuk());
        penebusanPupukStatus.setCreatedAt(LocalDate.now());
        penebusanPupukStatusRepository.save(penebusanPupukStatus);
    }
    String parseMonth(int month) {
        if (month == 1) return "Januari";
        else if (month == 2) return "Februari";
        else if (month == 3) return "Maret";
        else if (month == 4) return "April";
        else if (month == 5) return "Mei";
        else if (month == 6) return "Juni";
        else if (month == 7) return "Juli";
        else if (month == 8) return "Agustus";
        else if (month == 9) return "September";
        else if (month == 10) return "Oktober";
        else if (month == 11) return "November";
        else if (month == 12) return "Desember";
        else return null;
    }
}
