package com.kpb.notificationservice.dto.Attribut;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AttributeResponseDTO {
    private Long attributeNotifikasiId;
    private String icon;
    private String role;
    private String jenis;
    private boolean isRead;
}
