package com.kpb.notificationservice.dto.Notifikasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.notificationservice.domain.StatusNotifikasi;
import com.kpb.notificationservice.dto.Attribut.AttributeResponseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotifikasiResponseDTO {
    private Long notifikasiId;
    private Long userId;
    private String judul;
    private String pesan;
    private String createdAt;
    private String updatedAt;

    private AttributeResponseDTO attribute;
    private List<StatusNotifikasi> status;
}
