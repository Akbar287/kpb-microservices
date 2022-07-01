package com.kpb.creditservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "banks")
public class Bank implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283794526252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bank_id;

    @OneToOne(mappedBy = "bank")
    private BusinessCredit businessCredit;

    @Column(name = "type_bank", nullable = false)
    @NotNull(message = "type_bank cannot be null")
    @NotEmpty(message = "type_bank cannot be empty")
    private String type_bank;

    @Column(name = "file_logo", nullable = false)
    @NotNull(message = "file_logo cannot be null")
    @NotEmpty(message = "file_logo cannot be empty")
    private String file_logo;

    @Column(name = "bank_code", nullable = false)
    @NotNull(message = "bank_code cannot be null")
    @NotEmpty(message = "bank_code cannot be empty")
    private String bank_code;

    @Column(name = "bank_name", nullable = false)
    @NotNull(message = "bank_name cannot be null")
    @NotEmpty(message = "bank_name cannot be empty")
    private String bank_name;

    @Column(name = "leader_name", nullable = false)
    @NotNull(message = "leader_name cannot be null")
    @NotEmpty(message = "leader_name cannot be empty")
    private String leader_name;

    @Column(name = "created_at", nullable = false)
    private String created_at;
}
