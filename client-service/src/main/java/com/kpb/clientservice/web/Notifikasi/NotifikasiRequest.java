package com.kpb.clientservice.web.Notifikasi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotifikasiRequest {
    private Long userId;
    private String judul;
    private String pesan;

    public AttributeNotifikasi attribute;
}
