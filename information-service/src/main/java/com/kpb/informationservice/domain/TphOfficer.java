package com.kpb.informationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "tph_officer")
public class TphOfficer implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325752555479L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tph_office_id;

    @OneToMany(mappedBy = "tphOfficer")
    private List<Information> informationList;

    @Column(name = "leader_name", nullable = false)
    @NotNull(message = "leader name cannot be null")
    @NotEmpty(message = "leader name cannot be empty")
    private String leader_name;

    @Column(name = "isRegistered")
    private boolean isRegistered;

    @Column(name = "description")
    private String description;
}
