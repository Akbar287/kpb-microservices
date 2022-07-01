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
@Table(name = "klaim")
public class Klaim implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long klaimId;

    @JsonIgnore
    @OneToMany(mappedBy = "klaim")
    private List<FileKlaim> fileKlaim;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "klaim_asuransi", joinColumns = @JoinColumn(name = "klaim_id"), inverseJoinColumns = @JoinColumn(name = "pendaftaran_asuransi_id"))
    private PendaftaranAsuransi pendaftaranAsuransi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "klaim_usaha_tani", joinColumns = @JoinColumn(name = "klaim_id"), inverseJoinColumns = @JoinColumn(name = "usaha_tani_id"))
    private UsahaTani usahaTani;

    @Column( nullable = false)
    @NotNull(message = "coverage name cannot be null")
    @NotEmpty(message = "coverage name cannot be empty")
    private String namaKlaim;

    @Column( nullable = false)
    @NotNull(message = "coverage group cannot be null")
    @NotEmpty(message = "coverage group cannot be empty")
    private String kelompokKlaim;

    @Column( nullable = false)
    @NotNull(message = "code cannot be null")
    @NotEmpty(message = "code cannot be empty")
    private int kode;

    private boolean isTanggunganAsuransi;

    private boolean isTanggunganPetani;

    private String keterangan;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;

}
