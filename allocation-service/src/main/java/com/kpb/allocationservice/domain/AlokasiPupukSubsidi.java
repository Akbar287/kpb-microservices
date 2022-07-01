package com.kpb.allocationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "alokasi_pupuk_subsidi")
public class AlokasiPupukSubsidi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283789676252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alokasiPupukSubsidiId;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "pupuk_subsidi_id")
    private PupukSubsidi pupukSubsidi;

    @Column(nullable = false)
    @NotNull(message = "transaksi_id cannot be null")
    private Long transaksiId;

    @Column(nullable = false)
    @NotNull(message = "fertilizer_name cannot be null")
    @NotEmpty(message = "fertilizer_name cannot be empty")
    private String namaPupuk;

    @Column(nullable = false)
    @NotNull(message = "amount cannot be null")
    private int jumlah;
}
