package com.kpb.authservice.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users") @Data
public class User implements Serializable {

    private static final long serialVersionUID = -6283155325752559808L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(mappedBy = "user")
    private List<Area> area;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccounts;

    @Column(nullable = false, length = 64)
    @NotNull(message = "Name cannot be nullable")
    private String nama;

    @Email(message = "Email must be valid")
    @Column(unique = true, length = 128)
    private String email;

    @Length(message = "username must be 8 to 12 character", min = 8, max = 12)
    @NotNull(message = "username must be filled")
    @Column(nullable = false)
    private String username;

    @Column(length = 128)
    private String fileGambar;

    @NotNull(message = "password must be filled")
    @Column(nullable = false)
    private String password;

    @Length(message = "Phone Number must be 8-16 Character", min = 8, max = 16)
    @Column(unique = true)
    private String nomorTelepon;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;

}
