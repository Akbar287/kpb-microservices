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
import java.util.List;

@Entity
@Table(name="aset_petani")
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AsetPetani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752558809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asetPetaniId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "petani_id", nullable = false)
    private Petani petani;

    @Column(nullable = false)
    @NotNull(message = "asset name cannot be null")
    @NotEmpty(message = "asset name cannot be empty")
    private String namaAset;

    @Column(nullable = false)
    @NotNull(message = "asset total cannot be null")
    private int totalAset;

    @Column(nullable = false)
    @NotNull(message = "asset type cannot be null")
    @NotEmpty(message = "asset type cannot be empty")
    private String jenisAset;

    @OneToMany(mappedBy = "asetPetani")
    private List<MasaTanamPetani> masaTanamPetani;
}
