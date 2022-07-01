package com.kpb.authservice.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.authservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailsListResponseDTO {
    private Long userId;
    private String email;
    private String nama;
    private String fileGambar;
    private String username;
    private String nomorTelepon;
    private Collection<Role> roles;
    private String createdAt;
    private String updatedAt;
}
