package com.kpb.notificationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "notifikasi")
@Data
public class Notifikasi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6285455325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notifikasiId;

    @OneToMany(mappedBy = "notifikasi")
    private List<StatusNotifikasi> statusNotifikasi;

    @OneToOne(mappedBy = "notifikasi")
    private AttributNotifikasi attributNotifikasi;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false, length = 32)
    @NotNull(message = "judul cannot be null")
    @NotEmpty(message = "judul cannot be empty")
    private String judul;

    @Column(nullable = false)
    @NotNull(message = "pesan cannot be null")
    @NotEmpty(message = "pesan cannot be empty")
    private String pesan;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
