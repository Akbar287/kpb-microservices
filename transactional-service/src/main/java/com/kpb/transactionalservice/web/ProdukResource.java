package com.kpb.transactionalservice.web;

import com.kpb.clientservice.web.Transaksi.StokProdukResponse;
import com.kpb.transactionalservice.dto.PaginationResponse;
import com.kpb.transactionalservice.dto.Produk.ProdukListResponseDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukRequestDTO;
import com.kpb.transactionalservice.dto.Produk.ProdukResponseDTO;
import com.kpb.transactionalservice.service.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/transaction/api/produk")
public class ProdukResource {

    @Autowired
    private ProdukService produkService;

    @GetMapping
    public ResponseEntity<PaginationResponse> findAll(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "kategori", required = false) String kategori, @RequestParam(value = "jenis", required = false) String jenis, @RequestParam(value = "kios", required = false) Long kios,  @RequestParam(value = "userId", required = false) Long userId) {
        PaginationResponse paginationResponse = produkService.findAll(page, size,sort, search, kategori, jenis, kios, userId);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/poktan")
    public ResponseEntity<PaginationResponse> findAllPoktan(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "kategori", required = false) String kategori, @RequestParam(value = "jenis", required = false) String jenis, @RequestParam(value = "poktanUserId", required = false) Long poktanUserId) {
        PaginationResponse paginationResponse = produkService.findAllPoktan(page, size,sort, search, kategori, jenis, poktanUserId);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @GetMapping("/{produkId}")
    public ResponseEntity<ProdukResponseDTO> findDetail(@PathVariable("produkId") Long produkId) {
        ProdukResponseDTO produkResponseDTO = produkService.findDetail(produkId);
        return ResponseEntity.ok().body(produkResponseDTO);
    }

    @GetMapping("/penebusan")
    public ResponseEntity<List<ProdukListResponseDTO>> findAllProdukWithPenebusan() {
        List<ProdukListResponseDTO> produkResponseDTO = produkService.findAllProdukWithPenebusan();
        return ResponseEntity.ok().body(produkResponseDTO);
    }

    @GetMapping("/penebusan-stok")
    public ResponseEntity<StokProdukResponse> findStokProdukByNamaAndTahunAndBulan(@RequestParam("nama") String nama, @RequestParam("tahun") int tahun, @RequestParam("bulan") String bulan) {
        StokProdukResponse produkResponseDTO = produkService.findStokProdukByNamaAndTahunAndBulan(nama, tahun, bulan);
        return ResponseEntity.ok().body(produkResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProdukRequestDTO dto) throws URISyntaxException {
        produkService.create(dto);
        return ResponseEntity.created(new URI("/harga")).build();
    }

    @PutMapping("/{produkId}")
    public ResponseEntity<Void> update(@PathVariable("produkId") Long produkId, @RequestBody ProdukRequestDTO dto) {
        produkService.update(produkId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{produkId}")
    public ResponseEntity<Void> delete(@PathVariable("produkId") Long produkId) {
        produkService.delete(produkId);
        return ResponseEntity.noContent().build();
    }

}
