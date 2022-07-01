package com.kpb.authservice.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PermissionCreateRequestDTO {
    private String permissionName;
}
