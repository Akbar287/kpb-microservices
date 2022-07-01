package com.kpb.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "permission") @Data @AllArgsConstructor @NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = -6283155325752559806L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @NotEmpty(message = "Permission cannot be empty")
    @NotNull(message = "Permission cannot be null")
    @Column(nullable = false, length = 64)
    private String namaPermission;

}
