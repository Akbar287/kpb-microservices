package com.kpb.authservice.service.Impl;

import com.kpb.authservice.domain.Permission;
import com.kpb.authservice.dto.permission.PermissionCreateRequestDTO;
import com.kpb.authservice.dto.permission.PermissionDetailResponseDTO;
import com.kpb.authservice.exception.ResourceNotFoundException;
import com.kpb.authservice.repository.PermissionRepository;
import com.kpb.authservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionDetailResponseDTO> findAllPermission() {
        List<Permission> permissionsResponse = permissionRepository.findAll();
        return permissionsResponse.stream().map((b)-> {
            PermissionDetailResponseDTO dto = new PermissionDetailResponseDTO();
            dto.setPermissionId(b.getPermissionId());
            dto.setNamaPermission(b.getNamaPermission());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public PermissionDetailResponseDTO findPermission(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("permission.not.found"));
        PermissionDetailResponseDTO response = new PermissionDetailResponseDTO();
        response.setPermissionId(permission.getPermissionId());
        response.setNamaPermission(permission.getNamaPermission());
        return response;
    }

    @Override
    public PermissionDetailResponseDTO createPermission(PermissionCreateRequestDTO dto) {
        Permission permission = new Permission();
        permission.setNamaPermission(dto.getPermissionName());
        permissionRepository.save(permission);
        return this.findPermission(permission.getPermissionId());
    }


    @Override
    public void updatePermission(PermissionCreateRequestDTO dto, Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(()->new ResourceNotFoundException("Permission.not.found"));
        permission.setNamaPermission(dto.getPermissionName());
        permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
