package com.kpb.apigateway.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InformationGateway {
    @GetMapping
    public ResponseEntity<String> info () {
        String information = "this is Gateway";
        return ResponseEntity.ok().body(information);
    }
}
