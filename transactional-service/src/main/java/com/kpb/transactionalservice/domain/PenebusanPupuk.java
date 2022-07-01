package com.kpb.transactionalservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "penebusan_pupuk")
public class PenebusanPupuk implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752573555L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penebusanPupukId;

    @JsonIgnore
    @OneToMany(mappedBy = "penebusanPupuk")
    private List<PenebusanPupukStatus> penebusanPupukStatus;

    @JsonIgnore
    @OneToOne(mappedBy = "penebusanPupuk")
    private Transaksi transaksi;

    private boolean isPoktan;
    private boolean isPetani;
}
