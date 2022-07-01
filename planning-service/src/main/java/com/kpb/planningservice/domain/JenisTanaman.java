package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "jenis_tanaman")
public class JenisTanaman implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283760926252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jenisTanamanId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private Integer masaTanam;

    @Column(nullable = false)
    @NotNull(message = "plant_name cannot be null")
    @NotEmpty(message = "plant_name cannot be empty")
    private String namaTanaman;
}
