package com.kpb.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role") @Data @AllArgsConstructor @NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = -6283155325752559807L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotNull(message = "Role cannot be empty")
    @Column(nullable = false, unique = true, length = 64)
    private String namaRole;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> rolePermission;
}
