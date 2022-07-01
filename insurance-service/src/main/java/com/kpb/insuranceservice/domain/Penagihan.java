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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "penagihan")
public class Penagihan implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155342843259999L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penagihanId;

    @ManyToOne
    @JoinColumn(name = "pendaftaran_asuransi_id")
    private PendaftaranAsuransi pendaftaranAsuransi;

    @JsonIgnore
    @OneToMany(mappedBy = "penagihan")
    private List<PenagihanDetail> penagihanDetail;

    @Column(nullable = false)
    @NotNull(message = "due date cannot be null")
    @NotEmpty(message = "due date cannot be empty")
    private LocalDateTime batasWaktu;

    @Column(nullable = false)
    @NotNull(message = "minimum payment cannot be null")
    @NotEmpty(message = "minimum payment cannot be empty")
    private BigInteger minimalPembayaran;

    @Column(nullable = false)
    @NotNull(message = "balance cannot be null")
    @NotEmpty(message = "balance cannot be empty")
    private BigInteger saldo;

    @Column(nullable = false)
    @NotNull(message = "status cannot be null")
    @NotEmpty(message = "status cannot be empty")
    private String status;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
