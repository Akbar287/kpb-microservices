package com.kpb.authservice.service;

import com.kpb.authservice.dto.role.RoleCreateRequestDTO;
import com.kpb.authservice.dto.role.RoleDetailResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public List<RoleDetailResponseDTO> findAllRole();
    public RoleDetailResponseDTO findRole(Long roleId);
    public RoleDetailResponseDTO createRole(RoleCreateRequestDTO dto);
    public void addPermissionToRole(String permissionName, String roleName);
    public void updateRole(RoleCreateRequestDTO dto, Long roleId);
    public void deleteRole(Long roleId);
}
