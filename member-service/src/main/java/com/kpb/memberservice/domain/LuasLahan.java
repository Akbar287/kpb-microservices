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
@Data
@Table(name="luas_lahan")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LuasLahan implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752556809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long luasLahanId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "petani_id")
    private Petani petani;

    @Column(nullable = false)
    @NotNull(message = "planting time type cannot be null")
    private int jenisMasaTanam;

    @Column(nullable = false, length = 64)
    @NotNull(message = "surface planting area cannot be null")
    @NotEmpty(message = "surface planting area cannot be empty")
    private String luasLahan;

}
