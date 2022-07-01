package com.kpb.clientservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PLANNING-SERVICE")
public interface RutClient {

    @GetMapping("/planning/api/rencana/luas-lahan")
    Integer findLuasLahan(@RequestParam("petaniId") Long petaniId);
}
