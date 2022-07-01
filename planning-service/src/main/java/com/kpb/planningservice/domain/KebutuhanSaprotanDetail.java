package com.kpb.planningservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "kebutuhan_saprotan_detail")
public class KebutuhanSaprotanDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155894952559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kebutuhanSaprotanDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="kebutuhan_saprotan_id")
    private KebutuhanSaprotan kebutuhanSaprotan;

    @Column(nullable = false)
    @NotNull(message = "product_name cannot be null")
    @NotEmpty(message = "product_name cannot be empty")
    private String namaSaprotan;

    @Column(nullable = false)
    @NotNull(message = "amount cannot be null")
    private Integer jumlah;
}
