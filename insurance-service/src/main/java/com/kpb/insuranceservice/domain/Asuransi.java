package com.kpb.insuranceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "asuransi")
@Entity
public class Asuransi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283156655752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asuransiId;

    @JsonIgnore
    @OneToMany(mappedBy = "asuransi")
    private List<PendaftaranAsuransi> pendaftaranAsuransi;

    @JsonIgnore
    @OneToMany(mappedBy = "asuransi")
    private List<AsuransiEditLog> asuransiEditLogs;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pelaksana_asuransi_id")
    private PelaksanaAsuransi pelaksanaAsuransi;

    @Column( nullable = false)
    @NotNull(message = "nama asuransi cannot be null")
    @NotEmpty(message = "nama asuransi cannot be empty")
    private String namaAsuransi;

    @Column( nullable = false)
    @NotNull(message = "jenis asuransi cannot be null")
    @NotEmpty(message = "jenis asuransi cannot be empty")
    private String jenisAsuransi;

    @Column( nullable = false)
    @NotNull(message = "nama asuransi cannot be null")
    @NotEmpty(message = "nama asuransi cannot be empty")
    private Integer biaya;

    private String informasi;

    @Column( nullable = false)
    private boolean isPublished;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;

}
