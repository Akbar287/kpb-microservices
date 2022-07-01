package com.kpb.insuranceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="usaha_tani")
public class UsahaTani implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usahaTaniId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="pendaftaran_asuransi_id")
    private PendaftaranAsuransi pendaftaranAsuransi;

    @JsonIgnore
    @OneToMany(mappedBy = "usahaTani")
    private List<Klaim> klaim;

    @Column( nullable = false)
    @NotNull(message = "farmer id cannot be null")
    private Long petaniId;

    @Column( nullable = false)
    @NotNull(message = "year cannot be null")
    private int tahun;

    @Column( nullable = false)
    @NotNull(message = "planting time cannot be null")
    private int masaTanam;

    @Column( nullable = false)
    @NotNull(message = "planting time cannot be null")
    private int luasArea;

    @Column( nullable = false)
    @NotNull(message = "commodity name cannot be null")
    @NotEmpty(message = "commodity name cannot be empty")
    private String namaKomoditas;

    @Column( nullable = false)
    @NotNull(message = "subsector cannot be null")
    @NotEmpty(message = "subsector cannot be empty")
    private String subsektor;

    private boolean isAktif;

    @Column( nullable = false)
    private LocalDate createdAt;

    @Column( nullable = false)
    private LocalDate updatedAt;
}
