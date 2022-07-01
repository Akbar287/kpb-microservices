package com.kpb.informationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Table(name = "file_informaions")
public class FileInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283155325787659809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_information_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "information_id")
    private Information information;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String file_name;

    @Column(name = "file_upload", nullable = false)
    @NotNull(message = "file upload cannot be null")
    @NotEmpty(message = "file upload cannot be empty")
    private String file_upload;

}
