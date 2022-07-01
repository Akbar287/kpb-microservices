package com.kpb.transactionalservice.service.Impl;

import com.kpb.clientservice.feign.MemberClient;
import com.kpb.clientservice.web.CreateUserResponse;
import com.kpb.clientservice.web.Member.DistributorResponse;
import com.kpb.clientservice.web.Member.KiosResponse;
import com.kpb.clientservice.web.Transaksi.StokProdukResponse;
import com.kpb.transactionalservice.domain.*;
import com.kpb.transactionalservice.dto.Harga.HargaResponseDTO;
import com.kpb.transactionalservice.dto.Jenis.JenisResponseDTO;
import com.kpb.transactionalservice.dto.Kategori.KategoriResponseDTO;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.Produk.ProdukListResponseDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukRequestDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import com.kpb.transactionalservice.dto.Stok.StokResponseDTO;
import com.kpb.transactionalservice.exception.ResourceNotFoundException;
import com.kpb.transactionalservice.repository.*;
import com.kpb.transactionalservice.service.ProdukService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service @Slf4j
public class ProdukServiceImpl implements ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired
    private JenisRepository jenisRepository;

    @Autowired
    private HargaRepository hargaRepository;

    @Autowired
    private StokRepository stokRepository;

    @Autowired
    private MemberClient memberClient;

    @Autowired
    private ProdukDistributorRepository produkDistributorRepository;

    @Override
    public PaginationResponse findAll(int page, int size, String sort, String search, String kategoriText, String jenisText, Long kiosText, Long userId) {
        sort = (sort != null) ? sort : "produkId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Produk> produk = null;

        boolean b0 = !kategoriText.equals("") && jenisText.equals("");
        boolean b1 = !kategoriText.equals("") && !jenisText.equals("");
        boolean b2 = !jenisText.equals("") && kategoriText.equals("");

        if (!Objects.isNull(kiosText) || !Objects.isNull(userId)) {
            DistributorResponse distributorResponse = (!Objects.isNull(kiosText)) ? memberClient.findDistributorByKiosFromUser(kiosText) : memberClient.findDistributorByPetaniFromUser(userId);
            if(b1) {
                produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategoriAndJenis_NamaJenis(search, distributorResponse.getDistributorId(), kategoriText, jenisText, pageable);
            } else if(b0) {
                produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategori(search, distributorResponse.getDistributorId(), kategoriText, pageable);
            } else if(b2) {
                produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndJenis_NamaJenis(search, distributorResponse.getDistributorId(), jenisText, pageable);
            } else {
                produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorId(search, distributorResponse.getDistributorId(), pageable);
            }
        } else {
            if(b1) {
                produk = produkRepository.findAllByNamaProdukLikeAndKategori_NamaKategoriAndJenis_NamaJenis(search, kategoriText, jenisText, pageable);
            } else if(b0) {
                produk = produkRepository.findAllByNamaProdukLikeAndKategori_NamaKategori(search, kategoriText, pageable);
            } else if(b2) {
                produk = produkRepository.findAllByNamaProdukLikeAndJenis_NamaJenis(search, jenisText, pageable);
            } else {
                produk = produkRepository.findAllByNamaProdukLike(search, pageable);
            }
        }

        KiosResponse kiosResponse = null;
        if(!Objects.isNull(kiosText) || !Objects.isNull(userId)) {
            kiosResponse = (!Objects.isNull(kiosText)) ? memberClient.findKiosByUser(kiosText) : memberClient.findKiosByPetaniUser(userId);
        }

        KiosResponse finalKiosResponse = kiosResponse;
        List<ProdukResponseDTO> produkResponseDTOS = produk.stream().map(b->{
            HargaResponseDTO harga = new HargaResponseDTO();
            if(b.getHarga() != null) {
                harga.setHargaId(b.getHarga().getHargaId());
                harga.setHarga(b.getHarga().getHarga().intValue());
                harga.setHargaSubsidi(b.getHarga().getHargaSubsidi().intValue());
                harga.setHargaKios(b.getHarga().getHargaKios().intValue());
                harga.setHargaKiosSubsidi(b.getHarga().getHargaKiosSubsidi().intValue());
                harga.setBiayaKios(b.getHarga().getBiayaKios().intValue());
                harga.setBiayaKiosSubsidi(b.getHarga().getBiayaKiosSubsidi().intValue());
                harga.setBiayaLain(b.getHarga().getBiayaLain().intValue());
            }

            JenisResponseDTO jenis = new JenisResponseDTO();
            if(b.getJenis() != null) {
                jenis.setJenisId(b.getJenis().getJenisId());
                jenis.setNama(b.getJenis().getNamaJenis());
                jenis.setFileGambar(b.getJenis().getFileGambar());
                jenis.setDeskripsi(b.getJenis().getDescription());
                jenis.setCreatedAt(String.valueOf(b.getJenis().getCreatedAt()));
                jenis.setUpdatedAt(String.valueOf(b.getJenis().getUpdatedAt()));
            }

            KategoriResponseDTO kategori = new KategoriResponseDTO();
            if(b.getKategori() != null) {
                kategori.setKategoriId(b.getKategori().getKategoriId());
                kategori.setNamaKategori(b.getKategori().getNamaKategori());
                kategori.setFileGambar(b.getKategori().getFileGambar());
                kategori.setDeskripsi(b.getKategori().getDeskripsi());
                kategori.setCreatedAt(String.valueOf(b.getKategori().getCreatedAt()));
                kategori.setUpdatedAt(String.valueOf(b.getKategori().getUpdatedAt()));
            }

            String[] date = (LocalDate.now().toString().split("-"));
            StokResponseDTO stok = new StokResponseDTO();
            Stok stokThisMonth = b.getStok().stream().filter(filter-> filter.getBulan().equals(monthParse(Integer.parseInt(date[1]))) && filter.getTahun() == Integer.parseInt(date[0])).findFirst().orElse(null);
            if(stokThisMonth != null) {
                stok.setStokId(stokThisMonth.getStokKiosId());
                stok.setBulan(stokThisMonth.getBulan());
                stok.setTahun(stokThisMonth.getTahun());
                stok.setStokAwal(stokThisMonth.getStokAwal());
                stok.setStokSubsidi(stokThisMonth.getStokSubsidi());
                stok.setStokPenyaluran(stokThisMonth.getStokPenyaluran());
                stok.setStokAkhir(stokThisMonth.getStokAkhir());
                stok.setKios(finalKiosResponse);
            }

            ProdukResponseDTO dto = new ProdukResponseDTO();
            dto.setProdukId(b.getProdukId());
            dto.setFileGambar(b.getFileGambar());
            dto.setSatuan(b.getSatuan());
            dto.setNamaProduk(b.getNamaProduk());
            dto.setDeskripsi(b.getDeskripsi());
            dto.setKios(finalKiosResponse);
            dto.setPenebusan(b.isPenebusan());
            dto.setHarga(harga);
            dto.setJenis(jenis);
            dto.setKategori(kategori);
            dto.setStok(stok);
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(produkResponseDTOS);
        paginationResponse.setEmpty(produk.isEmpty());
        paginationResponse.setFirst(produk.isFirst());
        paginationResponse.setLast(produk.isLast());
        paginationResponse.setNumber(produk.getNumber());
        paginationResponse.setPageable(produk.getPageable());
        paginationResponse.setSize(produk.getSize());
        paginationResponse.setSort(produk.getSort());
        paginationResponse.setTotalElements(produk.getTotalElements());
        paginationResponse.setNumberOfElements(produk.getNumberOfElements());
        paginationResponse.setTotalPages(produk.getTotalPages());
        return paginationResponse;
    }

    @Override
    public PaginationResponse findAllPoktan(int page, int size, String sort, String search, String kategoriText, String jenisText, Long poktanUserId) {
        sort = (sort != null) ? sort : "produkId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";

        DistributorResponse distributorResponse = memberClient.findDistributorByPoktanFromUser(poktanUserId);
        KiosResponse kiosResponse = memberClient.findKiosByPoktanUser(poktanUserId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Produk> produk;

        if(!kategoriText.equals("") && !jenisText.equals("")) {
            produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategoriAndJenis_NamaJenis(search, distributorResponse.getDistributorId(), kategoriText, jenisText, pageable);
        } else if(!kategoriText.equals("") && jenisText.equals("")) {
            produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndKategori_NamaKategori(search, distributorResponse.getDistributorId(), kategoriText, pageable);
        } else if(!jenisText.equals("") && kategoriText.equals("")) {
            produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorIdAndJenis_NamaJenis(search, distributorResponse.getDistributorId(), jenisText, pageable);
        } else {
            produk = produkRepository.findAllByNamaProdukLikeAndProdukDistributor_DistributorId(search, distributorResponse.getDistributorId(), pageable);
        }

        List<ProdukResponseDTO> produkResponseDTOS = produk.stream().map(b->{
            HargaResponseDTO harga = new HargaResponseDTO();
            if(b.getHarga() != null) {
                harga.setHargaId(b.getHarga().getHargaId());
                harga.setHarga(b.getHarga().getHarga().intValue());
                harga.setHargaSubsidi(b.getHarga().getHargaSubsidi().intValue());
                harga.setHargaKios(b.getHarga().getHargaKios().intValue());
                harga.setHargaKiosSubsidi(b.getHarga().getHargaKiosSubsidi().intValue());
                harga.setBiayaKios(b.getHarga().getBiayaKios().intValue());
                harga.setBiayaKiosSubsidi(b.getHarga().getBiayaKiosSubsidi().intValue());
                harga.setBiayaLain(b.getHarga().getBiayaLain().intValue());
            }

            JenisResponseDTO jenis = new JenisResponseDTO();
            if(b.getJenis() != null) {
                jenis.setJenisId(b.getJenis().getJenisId());
                jenis.setNama(b.getJenis().getNamaJenis());
                jenis.setFileGambar(b.getJenis().getFileGambar());
                jenis.setDeskripsi(b.getJenis().getDescription());
                jenis.setCreatedAt(String.valueOf(b.getJenis().getCreatedAt()));
                jenis.setUpdatedAt(String.valueOf(b.getJenis().getUpdatedAt()));
            }

            KategoriResponseDTO kategori = new KategoriResponseDTO();
            if(b.getKategori() != null) {
                kategori.setKategoriId(b.getKategori().getKategoriId());
                kategori.setNamaKategori(b.getKategori().getNamaKategori());
                kategori.setFileGambar(b.getKategori().getFileGambar());
                kategori.setDeskripsi(b.getKategori().getDeskripsi());
                kategori.setCreatedAt(String.valueOf(b.getKategori().getCreatedAt()));
                kategori.setUpdatedAt(String.valueOf(b.getKategori().getUpdatedAt()));
            }

            String[] date = (LocalDate.now().toString().split("-"));
            StokResponseDTO stok = new StokResponseDTO();
            Stok stokThisMonth = b.getStok().stream().filter(filter-> filter.getBulan().equals(monthParse(Integer.parseInt(date[1]))) && filter.getTahun() == Integer.parseInt(date[0])).findFirst().orElse(null);
            if(stokThisMonth != null) {
                stok.setStokId(stokThisMonth.getStokKiosId());
                stok.setBulan(stokThisMonth.getBulan());
                stok.setTahun(stokThisMonth.getTahun());
                stok.setStokAwal(stokThisMonth.getStokAwal());
                stok.setStokSubsidi(stokThisMonth.getStokSubsidi());
                stok.setStokPenyaluran(stokThisMonth.getStokPenyaluran());
                stok.setStokAkhir(stokThisMonth.getStokAkhir());
                stok.setKios(kiosResponse);
            }

            ProdukResponseDTO dto = new ProdukResponseDTO();
            dto.setProdukId(b.getProdukId());
            dto.setFileGambar(b.getFileGambar());
            dto.setSatuan(b.getSatuan());
            dto.setNamaProduk(b.getNamaProduk());
            dto.setDeskripsi(b.getDeskripsi());
            dto.setKios(kiosResponse);
            dto.setPenebusan(b.isPenebusan());
            dto.setHarga(harga);
            dto.setJenis(jenis);
            dto.setKategori(kategori);
            dto.setStok(stok);
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(produkResponseDTOS);
        paginationResponse.setEmpty(produk.isEmpty());
        paginationResponse.setFirst(produk.isFirst());
        paginationResponse.setLast(produk.isLast());
        paginationResponse.setNumber(produk.getNumber());
        paginationResponse.setPageable(produk.getPageable());
        paginationResponse.setSize(produk.getSize());
        paginationResponse.setSort(produk.getSort());
        paginationResponse.setTotalElements(produk.getTotalElements());
        paginationResponse.setNumberOfElements(produk.getNumberOfElements());
        paginationResponse.setTotalPages(produk.getTotalPages());
        return paginationResponse;
    }

    @Override
    public ProdukResponseDTO findDetail(Long produkId) {
        Produk produk = produkRepository.findById(produkId).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));

        HargaResponseDTO harga = new HargaResponseDTO();
        harga.setHargaId(produk.getHarga().getHargaId());
        harga.setHarga(produk.getHarga().getHarga().intValue());
        harga.setHargaSubsidi(produk.getHarga().getHargaSubsidi().intValue());
        harga.setHargaKios(produk.getHarga().getHargaKios().intValue());
        harga.setHargaKiosSubsidi(produk.getHarga().getHargaKiosSubsidi().intValue());
        harga.setBiayaKios(produk.getHarga().getBiayaKios().intValue());
        harga.setBiayaKiosSubsidi(produk.getHarga().getBiayaKiosSubsidi().intValue());
        harga.setBiayaLain(produk.getHarga().getBiayaLain().intValue());

        JenisResponseDTO jenis = new JenisResponseDTO();
        jenis.setJenisId(produk.getJenis().getJenisId());
        jenis.setNama(produk.getJenis().getNamaJenis());
        jenis.setFileGambar(produk.getJenis().getFileGambar());
        jenis.setDeskripsi(produk.getJenis().getDescription());
        jenis.setCreatedAt(String.valueOf(produk.getJenis().getCreatedAt()));
        jenis.setUpdatedAt(String.valueOf(produk.getJenis().getUpdatedAt()));

        KategoriResponseDTO kategori = new KategoriResponseDTO();
        kategori.setKategoriId(produk.getKategori().getKategoriId());
        kategori.setNamaKategori(produk.getKategori().getNamaKategori());
        kategori.setFileGambar(produk.getKategori().getFileGambar());
        kategori.setDeskripsi(produk.getKategori().getDeskripsi());
        kategori.setCreatedAt(String.valueOf(produk.getKategori().getCreatedAt()));
        kategori.setUpdatedAt(String.valueOf(produk.getKategori().getUpdatedAt()));

        ProdukResponseDTO dto = new ProdukResponseDTO();
        dto.setProdukId(produk.getProdukId());
        dto.setFileGambar(produk.getFileGambar());
        dto.setSatuan(produk.getSatuan());
        dto.setNamaProduk(produk.getNamaProduk());
        dto.setDeskripsi(produk.getDeskripsi());
        dto.setPenebusan(produk.isPenebusan());
        dto.setHarga(harga);
        dto.setJenis(jenis);
        dto.setKategori(kategori);
        dto.setStok(null);
        return dto;
    }

    @Override
    public List<ProdukListResponseDTO> findAllProdukWithPenebusan() {
        List<Produk> produks = produkRepository.findAllByPenebusanIsTrue();

        return produks.stream().map(produk->{
            ProdukListResponseDTO dto = new ProdukListResponseDTO();
            dto.setProdukId(produk.getProdukId());
            dto.setNamaProduk(produk.getNamaProduk());
            dto.setDeskripsi(produk.getDeskripsi());
            dto.setFileGambar(produk.getFileGambar());
            dto.setSatuan(produk.getSatuan());
            dto.setPenebusan(produk.isPenebusan());
            return dto;
        }).toList();
    }

    @Override
    public StokProdukResponse findStokProdukByNamaAndTahunAndBulan(String nama, int tahun, String bulan) {
        Produk produk = produkRepository.findByNamaProduk(nama);
        if(produk.getStok().size() > 0) {
            Stok stok = produk.getStok().stream().filter(x->x.getTahun() == tahun && x.getBulan().equals(bulan)).findFirst().orElse(produk.getStok().get(produk.getStok().size() - 1));

            StokProdukResponse stokProdukResponse = new StokProdukResponse();
            stokProdukResponse.setProdukId(produk.getProdukId());
            stokProdukResponse.setKiosId(stok.getStokKiosId());
            stokProdukResponse.setDistributorId(stokProdukResponse.getDistributorId());
            stokProdukResponse.setTahun(stok.getTahun());
            stokProdukResponse.setBulan(stok.getBulan());
            stokProdukResponse.setKategori(produk.getKategori().getNamaKategori());
            stokProdukResponse.setJenis(produk.getJenis().getNamaJenis());
            stokProdukResponse.setHarga(produk.getHarga().getHarga().intValue());
            stokProdukResponse.setHarga_kios_subsidi(produk.getHarga().getHargaKiosSubsidi().intValue());
            stokProdukResponse.setHarga_subsidi(produk.getHarga().getHargaSubsidi().intValue());
            stokProdukResponse.setBiaya_kios(produk.getHarga().getBiayaKios().intValue());
            stokProdukResponse.setBiaya_kios_subsidi(produk.getHarga().getBiayaKiosSubsidi().intValue());
            stokProdukResponse.setStokAwal(stok.getStokAwal());
            stokProdukResponse.setStokAkhir(stok.getStokAkhir());
            stokProdukResponse.setStokPenyaluran(stok.getStokPenyaluran());
            stokProdukResponse.setStokSubsidi(stok.getStokSubsidi());
            return stokProdukResponse;
        } else {
            StokProdukResponse stokProdukResponse = new StokProdukResponse();
            return stokProdukResponse;
        }
    }

    @Override
    public void create(ProdukRequestDTO dto) {
        Kategori kategori = kategoriRepository.findByNamaKategori(dto.getKategori());
        Jenis jenis = jenisRepository.findByNamaJenis(dto.getJenis());

        Produk produk = new Produk();
        produk.setFileGambar(dto.getFileGambar());
        produk.setSatuan(dto.getSatuan());
        produk.setKategori(kategori);
        produk.setJenis(jenis);
        produk.setNamaProduk(dto.getNamaProduk());
        produk.setDeskripsi(dto.getDeskripsi());
        produk.setPenebusan(dto.isPenebusan());
        produk.setCreatedAt(LocalDate.now());
        produk.setUpdatedAt(LocalDate.now());
        produkRepository.save(produk);

        Harga harga = new Harga();
        harga.setHarga(dto.getHarga().getHarga());
        harga.setHargaSubsidi(dto.getHarga().getHargaSubsidi());
        harga.setHargaKios(dto.getHarga().getHargaKios());
        harga.setHargaKiosSubsidi(dto.getHarga().getHargaKiosSubsidi());
        harga.setBiayaKios(dto.getHarga().getBiayaKios());
        harga.setBiayaKiosSubsidi(dto.getHarga().getBiayaKiosSubsidi());
        harga.setBiayaLain(dto.getHarga().getBiayaLain());
        harga.setProduk(produk);
        hargaRepository.save(harga);

        List<Long>listAllDistributor = memberClient.findAllListDistributors();
        List<Produk> produks = new ArrayList<>();
        produks.add(produk);

        List<ProdukDistributor> produkDistributors = listAllDistributor.stream().map(distri -> {
            ProdukDistributor produkDistributor = new ProdukDistributor();
            produkDistributor.setProduk(produks);
            produkDistributor.setDistributorId(distri);
            return produkDistributor;
        }).toList();

        produkDistributorRepository.saveAll(produkDistributors);
    }

    @Override
    public void update(Long produkId, ProdukRequestDTO dto) {
        Produk produk = produkRepository.findById(produkId).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));
        try {
            Kategori kategori = kategoriRepository.findByNamaKategori(dto.getKategori());
            Jenis jenis = jenisRepository.findByNamaJenis(dto.getJenis());
            produk.setFileGambar(dto.getFileGambar());
            produk.setSatuan(dto.getSatuan());
            produk.setKategori(kategori);
            produk.setJenis(jenis);
            produk.setNamaProduk(dto.getNamaProduk());
            produk.setDeskripsi(dto.getDeskripsi());
            produk.setPenebusan(dto.isPenebusan());
            produk.setCreatedAt(LocalDate.now());
            produk.setUpdatedAt(LocalDate.now());
            produkRepository.save(produk);
        } catch (Exception e) {
            throw new ResourceNotFoundException("jenis.or.kategori.not.found");
        }
    }

    @Override
    public void delete(Long produkId) {
        Produk produk = produkRepository.findById(produkId).orElseThrow(()->new ResourceNotFoundException("produk.not.found"));
        if(produk.getProdukDistributor() != null) produkDistributorRepository.deleteAll(produk.getProdukDistributor());
        if(produk.getHarga() != null) hargaRepository.delete(produk.getHarga());
        if(produk.getStok() != null) stokRepository.deleteAll(produk.getStok());
        produkRepository.delete(produk);
    }

    String monthParse(int month) {
        if(month == 1) return "Januari";
        if(month == 2) return "Februari";
        if(month == 3) return "Maret";
        if(month == 4) return "April";
        if(month == 5) return "Mei";
        if(month == 6) return "Juni";
        if(month == 7) return "Juli";
        if(month == 8) return "Agustus";
        if(month == 9) return "September";
        if(month == 10) return "Oktober";
        if(month == 11) return "November";
        if(month == 12) return "Desember";
        return null;
    }
}
