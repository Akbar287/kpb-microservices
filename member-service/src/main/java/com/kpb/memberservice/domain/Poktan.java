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
@Table(name = "poktan")
public class Poktan implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155328752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poktanId;

    @ManyToOne
    @JoinColumn(name = "kios_id")
    private Kios kios;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "sub district code cannot be null")
    private int kodeDesa;

    @Column(nullable = false, length = 32)
    @NotNull(message = "sub district cannot be null")
    @NotEmpty(message = "sub district cannot be empty")
    private String namaDesa;

    @Column(nullable = false)
    private boolean statusKonfirmasi;

    @Column(nullable = false, length = 8)
    @NotNull(message = "farmer group type cannot be null")
    @NotEmpty(message = "farmer group type cannot be empty")
    private String jenisPoktan;

    @Column(nullable = false)
    private boolean registered;

    @Column(nullable = false, length = 32)
    @NotNull(message = "leader name cannot be null")
    @NotEmpty(message = "leader name cannot be empty")
    private String namaKetua;

    @Column(nullable = false, length = 32)
    @NotNull(message = "farmer_group_name cannot be null")
    @NotEmpty(message = "farmer_group_name cannot be empty")
    private String namaPoktan;

    @Column(nullable = false, length = 128)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "poktan", fetch = FetchType.EAGER)
    private List<Petani> petani;

}
