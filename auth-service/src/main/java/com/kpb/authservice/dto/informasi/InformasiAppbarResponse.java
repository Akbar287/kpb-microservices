package com.kpb.authservice.dto.informasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InformasiAppbarResponse {
    private int cartActive;
    private int notificationRead;
}
