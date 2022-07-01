package com.kpb.clientservice.feign;

import com.kpb.clientservice.web.Alokasi.AlokasiPupukCreateUpdateRequest;
import com.kpb.clientservice.web.Alokasi.AlokasiPupukSubsidiResponse;
import com.kpb.clientservice.web.Alokasi.SisaPupukRequest;
import com.kpb.clientservice.web.Alokasi.SisaPupukResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

@FeignClient("allocation-service")
public interface AlokasiClient {

    @PostMapping("/allocation/api/transaction")
    void createAllocation(AlokasiPupukCreateUpdateRequest alokasiPupukCreateUpdateRequest);

    @DeleteMapping("/allocation/api/transaction")
    void deleteAllocation(AlokasiPupukCreateUpdateRequest alokasiPupukCreateUpdateRequest);

    @GetMapping("/allocation/api/transaction/{transaksiDetailId}")
    AlokasiPupukSubsidiResponse getSisaAlokasiPubersByTransaksi(@PathVariable("transaksiDetailId") Long transaksiDetailId);

    @GetMapping("/allocation/api/pubers/sisa")
    SisaPupukResponse getSisaPupukByPetaniId(@RequestParam("petani") Long petani, @RequestParam("tahun") int tahun, @RequestParam("masa_tanam") int masa_tanam, @RequestParam(value = "pupuk", required = false) String pupuk);
}
