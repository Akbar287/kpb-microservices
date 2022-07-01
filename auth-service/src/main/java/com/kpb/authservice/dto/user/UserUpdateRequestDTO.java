package com.kpb.authservice.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateRequestDTO {
    private String email;
    private String nama;
    private String nomorTelepon;
    private String username;
}
