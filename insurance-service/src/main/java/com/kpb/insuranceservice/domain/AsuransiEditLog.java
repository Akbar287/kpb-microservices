package com.kpb.insuranceservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "asuransi_edit_log")
public class AsuransiEditLog implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752552109L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long asuransiEditLogId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "asuransi_id")
    private Asuransi asuransi;

    @Column( nullable = false)
    @NotNull(message = "edited table name cannot be null")
    @NotEmpty(message = "edited table name cannot be empty")
    private String namaTableEdit;

    @Column( nullable = false)
    @NotNull(message = "edited date cannot be null")
    @NotEmpty(message = "edited date cannot be empty")
    private LocalDate tanggalEdit;

    private String informasi;
}
