package com.kpb.allocationservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "pupuk_subsidi_status")
public class PupukSubsidiStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283787236252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pupukSubsidiStatusId;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "pupuk_subsidi_id")
    private PupukSubsidi pupukSubsidi;

    @Column(nullable = false)
    @NotNull(message = "label cannot be null")
    @NotEmpty(message = "label cannot be empty")
    private String label;

    @Column(nullable = false)
    @NotNull(message = "value cannot be null")
    @NotEmpty(message = "value cannot be empty")
    private String pesan;

    @Column(nullable = false)
    private LocalDate createdAt;
}
