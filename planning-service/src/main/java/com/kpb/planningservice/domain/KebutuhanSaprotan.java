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
@Table(name = "kebutuhan_saprotan")
public class KebutuhanSaprotan implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283194752622559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kebutuhanSaprotanId;

    @OneToMany(mappedBy = "kebutuhanSaprotan")
    private List<KebutuhanSaprotanDetail> kebutuhanSaprotanDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rencana_usaha_tani_id")
    private RencanaUsahaTani rencanaUsahaTani;

    @Column( nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private Integer masaTanam;

    @Column( nullable = false)
    @NotNull(message = "product_type cannot be null")
    @NotEmpty(message = "product_type cannot be empty")
    private String jenisSaprotan;
}
