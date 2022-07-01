package com.kpb.memberservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "distributor")
public class Distributor implements Serializable {
    @Serial
    private static final long serialVersionUID = -6285455325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long distributorId;

    @Column(nullable = false)
    @NotNull(message = "status cannot be null")
    private int status;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 128)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column(nullable = false, length = 32)
    @NotNull(message = "ahu number cannot be null")
    @NotEmpty(message = "ahu number cannot be empty")
    private String nomorAhu;

    @Column(nullable = false, length = 16)
    @NotNull(message = "distributor type cannot be null")
    @NotEmpty(message = "distributor type cannot be empty")
    private String jenisDistributor;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "distributor")
    private List<WilayahKerjaDistributor> workingAreaDistributors;

    @JsonIgnore
    @OneToMany(mappedBy = "distributor")
    private List<Kios> kiosList;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinTable(name = "pabrikan_distributor", joinColumns = {
            @JoinColumn(name = "distributor_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "pabrikan_id")
    })
    private Pabrikan pabrikan;

}
