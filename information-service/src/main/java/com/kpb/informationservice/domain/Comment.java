package com.kpb.informationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "comments")
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752932509L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="information_id")
    private Information information;

    @Column(name = "farmer_id", nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    @NotEmpty(message = "farmer_id cannot be empty")
    private Long farmer_id;

    @Column(name = "title", nullable = false)
    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Column(name = "content", nullable = false)
    @NotNull(message = "content cannot be null")
    @NotEmpty(message = "content cannot be empty")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

    @Column(name = "published_at", nullable = false)
    private LocalDate published_at;

}
