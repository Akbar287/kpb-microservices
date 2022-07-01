package com.kpb.informationservice.domain;

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
@Table(name = "informations")
public class Information implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155352952559809L;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tph_office_id")
    private TphOfficer tphOfficer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "information_categories", joinColumns = @JoinColumn(name = "information_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    @OneToMany(mappedBy = "information")
    private List<FileInformation> fileInformationList;

    @OneToMany(mappedBy = "information")
    private List<Comment> commentList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "information_tag", joinColumns = @JoinColumn(name = "information_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long information_id;

    @Column(name = "title", nullable = false)
    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Column(name = "meta_title", nullable = false)
    @NotNull(message = "meta title cannot be null")
    @NotEmpty(message = "meta title cannot be empty")
    private String meta_title;

    @Column(name = "commodity", nullable = false)
    @NotNull(message = "commodity cannot be null")
    @NotEmpty(message = "commodity cannot be empty")
    private String commodity;

    @Column(name = "slug", nullable = false)
    @NotNull(message = "slug cannot be null")
    @NotEmpty(message = "slug cannot be empty")
    private String slug;

    @Column(name = "summary", nullable = false)
    @NotNull(message = "summary cannot be null")
    @NotEmpty(message = "summary cannot be empty")
    private String summary;

    @Column(name = "published", nullable = false)
    @NotNull(message = "published cannot be null")
    @NotEmpty(message = "published cannot be empty")
    private String published;

    @Column(name = "content", nullable = false)
    @NotNull(message = "content cannot be null")
    @NotEmpty(message = "content cannot be empty")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;

    @Column(name = "published_at", nullable = false)
    private LocalDate published_at;

}
