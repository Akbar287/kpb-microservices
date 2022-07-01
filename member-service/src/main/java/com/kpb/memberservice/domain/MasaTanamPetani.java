package com.kpb.memberservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "masa_tanam_petani")
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MasaTanamPetani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752553809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long masaTanamPetaniId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aset_petani_id")
    private AsetPetani asetPetani;

    @Column(nullable = false)
    @NotNull(message = "planting time cannot be null")
    private int masaTanam;

    @Column(nullable = false)
    @NotNull(message = "commodity name cannot be null")
    @NotEmpty(message = "commodity name cannot be empty")
    private String namaKomoditas;

    @Column(nullable = false)
    @NotNull(message = "subsector cannot be null")
    @NotEmpty(message = "subsector cannot be empty")
    private String subsektor;

    @Column(nullable = false)
    @NotNull(message = "amount cannot be null")
    @NotEmpty(message = "amount cannot be empty")
    private String jumlah;
}
