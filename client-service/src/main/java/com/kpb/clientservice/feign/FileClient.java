package com.kpb.clientservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FILE-UPLOAD-SERVICE")
public interface FileClient {

    @GetMapping("/file/api/pubers/kategori/{name}")
    public feign.Response getFileKategori(@PathVariable("name") String name);

}
