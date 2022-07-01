package com.kpb.authservice.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserPasswordUpdateDTO {
    private String passwordBaru;
    private String passwordLama;
    private String konfirmasiPassword;
}
