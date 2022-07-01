package com.kpb.insuranceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "dinas_kabupaten")
public class DinasKabupaten implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155332752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dinasKabupatenId;

    @JsonIgnore
    @OneToMany(mappedBy = "dinasKabupaten")
    private List<DaftarPeserta> daftarPeserta;

    @Column(nullable = false)
    private Long userId;

    @Column( nullable = false)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column( nullable = false)
    @NotNull(message = "leader name cannot be null")
    @NotEmpty(message = "leader name cannot be empty")
    private String namaKetua;

    @Column( nullable = false)
    @NotNull(message = "region code cannot be null")
    private int kodeKabupaten;

    @Column( nullable = false)
    @NotNull(message = "region name cannot be null")
    @NotEmpty(message = "region name cannot be empty")
    private String namaKabupaten;

    private boolean isRegister;

    private boolean isKota;

}
