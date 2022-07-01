package com.kpb.authservice.repository;

import com.kpb.authservice.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Permission findByNamaPermission(String permissionName);
}
