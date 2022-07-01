package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transcripts")
public class Transcript implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752557564L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transcript_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "transcript_date", nullable = false)
    @NotNull(message = "transcript date cannot be null")
    @NotEmpty(message = "transcript date cannot be empty")
    private LocalDate transcript_date;

    @Column(name = "transcript_detail", nullable = false)
    @NotNull(message = "transcript detail cannot be null")
    @NotEmpty(message = "transcript detail cannot be empty")
    private String transcript_detail;

    @Column(name = "file_name", nullable = false)
    @NotNull(message = "file name cannot be null")
    @NotEmpty(message = "file name cannot be empty")
    private String file_name;

    @Column(name = "file_upload", nullable = false)
    @NotNull(message = "file upload cannot be null")
    @NotEmpty(message = "file upload cannot be empty")
    private String file_upload;

    @Column(name = "other_details", nullable = false)
    @NotNull(message = "other details cannot be null")
    @NotEmpty(message = "other details cannot be empty")
    private String other_details;

}
