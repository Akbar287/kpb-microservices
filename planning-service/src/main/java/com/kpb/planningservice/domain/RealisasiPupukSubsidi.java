package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "realisasi_pupuk_subsidi")
public class RealisasiPupukSubsidi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283198566252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realisasiPupukSubsidiId;

    @OneToMany(mappedBy = "realisasiPupukSubsidi")
    private List<RealisasiPupukSubsidiDetail> realisasiPupukSubsidiDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column(name = "planting_time", nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private Integer masaTanam;
}
