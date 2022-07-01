package com.kpb.clientservice.feign;

import com.kpb.clientservice.web.Notifikasi.NotifikasiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NOTIFICATION-SERVICE")
public interface NotifikasiClient {

    @PostMapping("/notifikasi/api/notifikasi")
    void createNotifikasi(@RequestBody NotifikasiRequest notifikasiRequest);

}
