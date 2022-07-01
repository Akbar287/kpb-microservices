package com.kpb.scholarshipservice.domain;

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
@Table(name = "students")
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752557632L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @OneToMany(mappedBy = "student")
    private List<Achievement> achievementList;

    @OneToMany(mappedBy = "student")
    private List<ScholarshipStudent> scholarshipStudentList;

    @OneToMany(mappedBy = "student")
    private List<Transcript> transcriptList;

    @Column(name = "farmer_id", nullable = false)
    @NotNull(message = "Farmer cannot be null")
    @NotEmpty(message = "Farmer name cannot be empty")
    private Long farmer_id;

    @Column(name = "student_name", nullable = false)
    @NotNull(message = "student name cannot be null")
    @NotEmpty(message = "student name cannot be empty")
    private String student_name;

    @Column(name = "student_address", nullable = false)
    @NotNull(message = "student address cannot be null")
    @NotEmpty(message = "student address cannot be empty")
    private String student_address;

    @Column(name = "student_phone", nullable = false)
    @NotNull(message = "student phone cannot be null")
    @NotEmpty(message = "student phone cannot be empty")
    private String student_phone;

    @Column(name = "student_gender", nullable = false)
    @NotNull(message = "student gender cannot be null")
    @NotEmpty(message = "student gender cannot be empty")
    private String student_gender;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;

}
