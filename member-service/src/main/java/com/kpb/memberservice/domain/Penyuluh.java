package com.kpb.memberservice.domain;

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
@Table(name = "penyuluh")
public class Penyuluh implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283235325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penyuluhId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 128)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column(nullable = false)
    private boolean verifikasi;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "penyuluh")
    private List<Kios> kiosList;

}
