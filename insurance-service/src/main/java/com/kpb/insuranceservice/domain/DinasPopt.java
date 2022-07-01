package com.kpb.insuranceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "dinas_popt")
public class DinasPopt implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155325752559734L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dinasPoptId;

    @JsonIgnore
    @OneToMany(mappedBy = "dinasPopt")
    private List<FileKlaim> fileKlaimList;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String alamat;

    @Column( nullable = false)
    @NotNull(message = "leader name cannot be null")
    @NotEmpty(message = "leader name cannot be empty")
    private String namaKetua;

    private boolean isRegister;

}
