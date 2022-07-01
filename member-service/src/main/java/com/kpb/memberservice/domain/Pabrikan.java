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
@Table(name = "pabrikan")
public class Pabrikan implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283124325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pabrikanId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 16)
    @NotNull(message = "factory type cannot be null")
    @NotEmpty(message = "factory type cannot be empty")
    private String jenisPabrik;

    @Column(nullable = false, length = 128)
    @NotNull(message = "alamat cannot be null")
    @NotEmpty(message = "alamat cannot be empty")
    private String alamat;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "pabrikan")
    private List<Distributor> distributor;
}
