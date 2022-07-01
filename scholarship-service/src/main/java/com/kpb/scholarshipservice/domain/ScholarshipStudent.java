package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "scholarship_students")
public class ScholarshipStudent implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752558790L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scholarship_student_id;

    @OneToMany(mappedBy = "scholarshipStudent")
    private List<ApplicantStatus> applicantStatusList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "scholarship_id")
    private Scholarships scholarships;

    @Column(name = "isRequirement")
    private boolean isRequirement;

    @Column(name = "isAward")
    private boolean isAward;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;
}
