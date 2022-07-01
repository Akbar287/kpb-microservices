package com.kpb.notificationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "status_notifikasi")
public class StatusNotifikasi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6285455325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusNotifikasiId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notifikasi_id")
    private Notifikasi notifikasi;

    @Column(name = "label", nullable = false)
    @NotNull(message = "label cannot be null")
    @NotEmpty(message = "label cannot be empty")
    private String label;

    @Column(name = "value", nullable = false)
    @NotNull(message = "value cannot be null")
    @NotEmpty(message = "value cannot be empty")
    private String value;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
