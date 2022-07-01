package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "commitees")
public class Committee implements Serializable {

    @Serial
    private static final long serialVersionUID = -6285855325752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long committee_id;

    @Column(name = "committee_name", nullable = false)
    @NotNull(message = "committee name cannot be null")
    @NotEmpty(message = "committee name cannot be empty")
    private String committee_name;

    @Column(name = "committee_members_name", nullable = false)
    @NotNull(message = "committee members name cannot be null")
    @NotEmpty(message = "committee members name cannot be empty")
    private String committee_members_name;

    @Column(name = "department", nullable = false)
    @NotNull(message = "department cannot be null")
    @NotEmpty(message = "department cannot be empty")
    private String department;

    @Column(name = "committee_contact_name", nullable = false)
    @NotNull(message = "committee contact name cannot be null")
    @NotEmpty(message = "committee contact name cannot be empty")
    private String committee_contact_name;

    @Column(name = "isRegistered")
    private boolean isRegistered;

    @OneToMany(mappedBy = "committee")
    private List<Scholarships> scholarships;

}
