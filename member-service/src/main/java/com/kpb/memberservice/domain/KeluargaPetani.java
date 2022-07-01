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
@Data
@Table(name = "keluarga_petani")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KeluargaPetani implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752559802L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keluargaPetaniId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "petani_id")
    private Petani petani;

    @Column(nullable = false)
    @NotNull(message = "Patriarch type cannot be null")
    @NotEmpty(message = "Patriarch type cannot be empty")
    private String kepalaKeluarga;

    @OneToMany(mappedBy = "keluargaPetani")
    private List<TanggunganPetani> tanggunganPetaniList;

    @OneToMany(mappedBy = "keluargaPetani")
    private List<TanggunganLain> tanggunganLainList;
}
