package com.kpb.clientservice.feign;

import com.kpb.clientservice.web.Transaksi.StokProdukResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TRANSACTIONAL-SERVICE")
public interface TransaksiClient {

    @GetMapping("/transaction/api/transaksi/informasi/{userId}/{role}")
    int getTransaksiAktif(@PathVariable("userId") Long userId, @PathVariable("role") String role);

    @GetMapping("/transaction/api/produk/penebusan-stok")
    StokProdukResponse getStokProdukbyNamaProdukAndTahunAndBulan(@RequestParam("nama") String nama, @RequestParam("tahun") int tahun, @RequestParam("bulan") String bulan);
}
