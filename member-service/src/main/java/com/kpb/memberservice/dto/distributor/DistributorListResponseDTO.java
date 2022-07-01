package com.kpb.memberservice.dto.distributor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kpb.memberservice.domain.WilayahKerjaDistributor;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistributorListResponseDTO {
    private Long distributorId;
    private String nama;
    private String alamat;
    private String nomorAhu;
    private String jenisDistributor;
    private int status;

    private List<WilayahKerjaDistributor> workingAreaDistributors;
}
