package com.kpb.authservice.web;

import com.kpb.authservice.dto.role.RoleCreateRequestDTO;
import com.kpb.authservice.dto.role.RoleDetailResponseDTO;
import com.kpb.authservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auth/api")
public class RoleResource {

    @Autowired
    private RoleService roleService;
    @GetMapping("/role")
    public ResponseEntity<List<RoleDetailResponseDTO>> getAllRole () {
        List<RoleDetailResponseDTO> result = roleService.findAllRole();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDetailResponseDTO> createRole (@RequestBody RoleCreateRequestDTO dto) throws URISyntaxException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        RoleDetailResponseDTO result = roleService.createRole(dto);
        return ResponseEntity.created(uri).body(result);
    }
}
