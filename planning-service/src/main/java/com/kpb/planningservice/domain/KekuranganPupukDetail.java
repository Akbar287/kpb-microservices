package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "kekurangan_pupuk_detail")
public class KekuranganPupukDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155888252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kekuranganPupukDetaiId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kekurangan_pupuk_id")
    private KekuranganPupuk kekuranganPupuk;

    @Column(nullable = false)
    @NotNull(message = "fertilizer_name cannot be null")
    @NotEmpty(message = "fertilizer_name cannot be empty")
    private String namaPupuk;

    @Column(nullable = false)
    @NotNull(message = "amount cannot be null")
    private Integer jumlah;
}
