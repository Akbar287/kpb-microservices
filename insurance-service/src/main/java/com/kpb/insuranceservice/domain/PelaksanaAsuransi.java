package com.kpb.insuranceservice.domain;

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
@Table(name = "pelaksana_asuransi")
public class PelaksanaAsuransi implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155125752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pelaksanaAsuransiId;

    @JsonIgnore
    @OneToMany(mappedBy = "pelaksanaAsuransi")
    private List<Asuransi> asuransi;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "insurance type cannot be null")
    @NotEmpty(message = "insurance type cannot be empty")
    private String jenisAsuransi;

    @Column(nullable = false)
    @NotNull(message = "leader name cannot be null")
    @NotEmpty(message = "leader name cannot be empty")
    private String namaKetua;

    @Column(nullable = false)
    @NotNull(message = "description cannot be null")
    @NotEmpty(message = "description cannot be empty")
    private String keterangan;

    @Column(nullable = false)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

}
