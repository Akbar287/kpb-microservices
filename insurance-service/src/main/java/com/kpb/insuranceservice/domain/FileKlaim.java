package com.kpb.insuranceservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "file_klaim")
public class FileKlaim implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283154325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileKlaimId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "klaim_id")
    private Klaim klaim;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="dinas_popt_id")
    private DinasPopt dinasPopt;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String namaFile;

    @Column(name = "file_upload", nullable = false)
    @NotNull(message = "file upload cannot be null")
    @NotEmpty(message = "file upload cannot be empty")
    private String namaDokumen;

    @Column(name = "type_file_coverage", nullable = false)
    @NotNull(message = "type file coverage cannot be null")
    @NotEmpty(message = "type file coverage cannot be empty")
    private String jenisKlaimFile;

}
