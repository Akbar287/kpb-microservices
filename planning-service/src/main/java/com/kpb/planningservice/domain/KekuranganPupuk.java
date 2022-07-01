package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "kekurangan_pupuk")
public class KekuranganPupuk implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155879352559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kekuranganPupukId;

    @OneToMany(mappedBy = "kekuranganPupuk")
    private List<KekuranganPupukDetail> kekuranganPupukDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column(nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private Integer masaTanam;
}
