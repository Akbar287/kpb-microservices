package com.kpb.insuranceservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "daftar_peserta")
public class DaftarPeserta implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752559834L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long daftarPesertaId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dinas_kabupaten_id")
    private DinasKabupaten dinasKabupaten;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "daftar_peserta_asuransi", joinColumns = @JoinColumn(name = "daftar_peserta_id"), inverseJoinColumns = @JoinColumn(name = "pendaftaran_asuransi_id"))
    private List<PendaftaranAsuransi> pendaftaranAsuransi;

    @Column( nullable = false)
    @NotNull(message = "year cannot be null")
    @NotEmpty(message = "year cannot be empty")
    private int tahun;

    @Column( nullable = false)
    @NotNull(message = "planting time cannot be null")
    @NotEmpty(message = "planting time cannot be empty")
    private int masaTanam;

    @Column( nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String namaFile;

    @Column( nullable = false)
    @NotNull(message = "file upload cannot be null")
    @NotEmpty(message = "file upload cannot be empty")
    private String namaDokumen;

    @Column()
    private int keterangan;

}
