package com.kpb.authservice.service;

import com.kpb.authservice.dto.permission.PermissionCreateRequestDTO;
import com.kpb.authservice.dto.permission.PermissionDetailResponseDTO;

import java.util.List;

public interface PermissionService {
    public List<PermissionDetailResponseDTO> findAllPermission();
    public PermissionDetailResponseDTO findPermission(Long permissionId);
    public PermissionDetailResponseDTO createPermission(PermissionCreateRequestDTO dto);
    public void updatePermission(PermissionCreateRequestDTO dto, Long permissionId);
    public void deletePermission(Long permissionId);
}
