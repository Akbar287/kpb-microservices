package com.kpb.scholarshipservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "scholarships")
public class Scholarships implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283213445752559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scholarship_id;

    @OneToMany(mappedBy = "scholarships")
    private List<ScholarshipRound> scholarshipRoundList;

    @OneToOne(mappedBy = "scholarships")
    private ScholarshipContactInformation scholarshipContactInformation;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "award_criteria_scholarship", joinColumns = @JoinColumn(name="award_criteria_id"), inverseJoinColumns = @JoinColumn(name = "scholarship_id"))
    private List<AwardCriteria> awardCriteriaList;

    @OneToMany(mappedBy = "scholarships")
    private List<ScholarshipStudent> scholarshipStudentList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="committe_id")
    private Committee committee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="scholarship_type_id")
    private ScholarshipType scholarshipType;

    @Column(name = "scholarship_name", nullable = false)
    @NotNull(message = "scholarship name cannot be null")
    @NotEmpty(message = "scholarship name cannot be empty")
    private String scholarship_name;

    @Column(name = "scholarship_type", nullable = false)
    @NotNull(message = "scholarship typ cannot be null")
    @NotEmpty(message = "scholarship type cannot be empty")
    private String scholarship_type;

    @Column(name = "aggrement_on_file", nullable = false)
    @NotNull(message = "aggrement on file cannot be null")
    @NotEmpty(message = "aggrement on file cannot be empty")
    private String aggrement_on_file;

    @Column(name = "number_of_donors", nullable = false)
    @NotNull(message = "number of donors cannot be null")
    @NotEmpty(message = "number of donors cannot be empty")
    private int number_of_donors;

    @Column(name = "purpose_restriction", nullable = false)
    @NotNull(message = "purpose restriction cannot be null")
    @NotEmpty(message = "purpose restriction cannot be empty")
    private String purpose_restriction;

    @Column(name = "period", nullable = false)
    @NotNull(message = "period cannot be null")
    @NotEmpty(message = "period cannot be empty")
    private String period;

    @Column(name = "spending_criteria", nullable = false)
    @NotNull(message = "spending criteria cannot be null")
    @NotEmpty(message = "spending criteria cannot be empty")
    private String spending_criteria;

    @Column(name = "description_scholarship_process", nullable = false)
    @NotNull(message = "description scholarship process cannot be null")
    @NotEmpty(message = "description scholarship process cannot be empty")
    private String description_scholarship_process;

    @Column(name = "legal_note", nullable = false)
    @NotNull(message = "legal note cannot be null")
    @NotEmpty(message = "legal note cannot be empty")
    private String legal_note;

    @Column(name = "total_amount", nullable = false)
    @NotNull(message = "total amount cannot be null")
    @NotEmpty(message = "total amount cannot be empty")
    private BigInteger total_amount;

    @Column(name = "estimated_size_each_award", nullable = false)
    @NotNull(message = "estimated size each_award cannot be null")
    @NotEmpty(message = "estimated size each_award cannot be empty")
    private String estimated_size_each_award;

    @Column(name = "estimated_number_award_offered", nullable = false)
    @NotNull(message = "estimated number award_offered cannot be null")
    @NotEmpty(message = "estimated number award_offered cannot be empty")
    private String estimated_number_award_offered;

    @Column(name = "offering_note", nullable = false)
    @NotNull(message = "offering note cannot be null")
    @NotEmpty(message = "offering note cannot be empty")
    private String offering_note;

    @Column(name = "data_created", nullable = false)
    private LocalDate data_created;

    @Column(name = "data_closed", nullable = false)
    private LocalDate data_closed;

}
