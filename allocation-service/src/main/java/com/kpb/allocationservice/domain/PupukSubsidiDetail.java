package com.kpb.allocationservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pupuk_subsidi_detail")
public class PupukSubsidiDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6282156526252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pupukSubsidiDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pupuk_subsidi_id")
    private PupukSubsidi pupukSubsidi;

    @Column( nullable = false)
    @NotNull(message = "fertilizer_name cannot be null")
    @NotEmpty(message = "fertilizer_name cannot be empty")
    private String namaPupuk;

    @Column( nullable = false)
    @NotNull(message = "amount cannot be null")
    private int jumlah;

    public Long getPupukSubsidiDetailId() {
        return pupukSubsidiDetailId;
    }

    public void setPupukSubsidiDetailId(Long pupukSubsidiDetailId) {
        this.pupukSubsidiDetailId = pupukSubsidiDetailId;
    }

    public PupukSubsidi getPupukSubsidi() {
        return pupukSubsidi;
    }

    public void setPupukSubsidi(PupukSubsidi pupukSubsidi) {
        this.pupukSubsidi = pupukSubsidi;
    }

    public String getNamaPupuk() {
        return namaPupuk;
    }

    public void setNamaPupuk(String namaPupuk) {
        this.namaPupuk = namaPupuk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
