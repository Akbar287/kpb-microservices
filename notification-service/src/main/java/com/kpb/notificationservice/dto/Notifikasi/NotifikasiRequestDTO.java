package com.kpb.notificationservice.dto.Notifikasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.notificationservice.dto.Attribut.AttributeRequestCreateUpdateDTO;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotifikasiRequestDTO {
    private Long userId;
    private String judul;
    private String pesan;

    public AttributeRequestCreateUpdateDTO attribute;
}
