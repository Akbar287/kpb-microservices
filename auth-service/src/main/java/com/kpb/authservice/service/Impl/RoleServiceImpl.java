package com.kpb.authservice.service.Impl;

import com.kpb.authservice.domain.Permission;
import com.kpb.authservice.domain.Role;
import com.kpb.authservice.dto.role.RoleCreateRequestDTO;
import com.kpb.authservice.dto.role.RoleDetailResponseDTO;
import com.kpb.authservice.exception.ResourceNotFoundException;
import com.kpb.authservice.repository.PermissionRepository;
import com.kpb.authservice.repository.RoleRepository;
import com.kpb.authservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RoleDetailResponseDTO> findAllRole() {
        List<Role> roleResponse = roleRepository.findAll();
        return roleResponse.stream().map((b)-> {
            RoleDetailResponseDTO dto = new RoleDetailResponseDTO();
            dto.setRoleId(b.getRoleId());
            dto.setNamaRole(b.getNamaRole());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RoleDetailResponseDTO findRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("role.not.found"));
        RoleDetailResponseDTO response = new RoleDetailResponseDTO();
        response.setRoleId(role.getRoleId());
        response.setNamaRole(role.getNamaRole());
        return response;
    }

    @Override
    public RoleDetailResponseDTO createRole(RoleCreateRequestDTO dto) {
        Role role = new Role();
        role.setNamaRole(dto.getNamaRole());
        roleRepository.save(role);
        return this.findRole(role.getRoleId());
    }

    @Override
    public void addPermissionToRole(String permissionName, String roleName) {
        Permission permission = permissionRepository.findByNamaPermission(permissionName);
        Role role = roleRepository.findByNamaRole(roleName);
        List<Permission> myPermission = new ArrayList<>();
        myPermission.add(permission);
        role.setRolePermission(myPermission);
        roleRepository.save(role);
    }

    @Override
    public void updateRole(RoleCreateRequestDTO dto, Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("role.not.found"));
        role.setNamaRole(dto.getNamaRole());
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
