package com.kpb.transactionalservice.dto.Transaksi;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransaksiUpdateRequestDTO {
    private Long TransaksiId;
    private String status;

}
