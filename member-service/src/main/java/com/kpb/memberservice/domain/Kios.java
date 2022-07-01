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
@Table(name = "kios")
public class Kios implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325722559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kiosId;

    @Column(nullable = false, length = 32)
    @NotNull(message = "pich code cannot be null")
    @NotEmpty(message = "pich code cannot be empty")
    private String kodePihc;

    @Column(nullable = false, length = 32)
    @NotNull(message = "commodity name cannot be null")
    @NotEmpty(message = "commodity name cannot be empty")
    private String namaKomoditas;

    @Column(nullable = false)
    private Boolean verifikasiPasarBebas;

    @Column(nullable = false, length = 128)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "kios")
    private List<WilayahKerjaKios> wilayahKerjaKios;

    @JsonIgnore
    @OneToMany(mappedBy = "kios")
    private List<Poktan> poktan;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinTable(name = "kios_penyuluh", joinColumns = {
            @JoinColumn(name = "kios_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "penyuluh_id")
    })
    private Penyuluh penyuluh;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinTable(name = "kios_distributor", joinColumns = {
            @JoinColumn(name = "kios_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "distributor_id")
    })
    private Distributor distributor;

}
