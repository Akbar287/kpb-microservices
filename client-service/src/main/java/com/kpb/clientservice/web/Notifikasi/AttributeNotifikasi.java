package com.kpb.clientservice.web.Notifikasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttributeNotifikasi {
    private Long notifikasiId;
    private String icon;
    private String role;
    private String jenis;
    private boolean isRead;
}
