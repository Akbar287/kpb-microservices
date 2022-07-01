package com.kpb.transactionalservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "penebusan_pupuk_status")
public class PenebusanPupukStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325758875809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penebusanPupukStatusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "penebusan_pupuk_id")
    private PenebusanPupuk penebusanPupuk;

    @Column(nullable = false)
    @NotNull(message = "label cannot be null")
    @NotEmpty(message = "label cannot be empty")
    private String label;

    @Column(nullable = false)
    @NotNull(message = "value cannot be null")
    @NotEmpty(message = "value cannot be empty")
    private String pesan;

    @Column(nullable = false)
    private LocalDate createdAt;

}
