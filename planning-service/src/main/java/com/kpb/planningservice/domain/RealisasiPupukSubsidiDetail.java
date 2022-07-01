package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "realisasi_pupuk_subsidi_detail")
public class RealisasiPupukSubsidiDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155378562559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realisasiPupukSubsidiDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "realisasi_pupuk_subsidi_id")
    private RealisasiPupukSubsidi realisasiPupukSubsidi;

    @Column(nullable = false)
    @NotNull(message = "fertilizer_name cannot be null")
    @NotEmpty(message = "fertilizer_name cannot be empty")
    private String namaPupuk;

    @Column(nullable = false)
    @NotNull(message = "amount cannot be null")
    private Integer jumlah;
}
