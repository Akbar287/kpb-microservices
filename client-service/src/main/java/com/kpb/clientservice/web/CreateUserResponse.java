package com.kpb.clientservice.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateUserResponse {
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
