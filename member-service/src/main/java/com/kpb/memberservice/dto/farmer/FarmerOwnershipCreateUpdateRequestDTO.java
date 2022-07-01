package com.kpb.memberservice.dto.farmer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FarmerOwnershipCreateUpdateRequestDTO {
    private String fasilitasListrik;
    private String statusRumah;
    private String statusTanah;
    private String kendaraan;
}
