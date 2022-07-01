package com.kpb.notificationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "attribut_notifikasi")
@Data
public class AttributNotifikasi implements Serializable {
    @Serial
    private static final long serialVersionUID = -655455325752559809L;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notifikasi_id")
    private Notifikasi notifikasi;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attributeNotifikasiId;

    @Column(nullable = false, length = 32)
    @NotNull(message = "role cannot be null")
    @NotEmpty(message = "role cannot be empty")
    private String role;

    @Column(nullable = false, length = 16)
    @NotNull(message = "icon cannot be null")
    @NotEmpty(message = "icon cannot be empty")
    private String icon;

    @Column(nullable = false, length = 8)
    @NotNull(message = "jenis cannot be null")
    @NotEmpty(message = "jenis cannot be empty")
    private String jenis;

    @Column()
    private boolean isRead;

}
