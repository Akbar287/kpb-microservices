package com.kpb.allocationservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pupuk_subsidi")
public class PupukSubsidi implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283799653252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pupukSubsidiId;

    @JsonIgnore
    @OneToMany(mappedBy = "pupukSubsidi")
    private List<PupukSubsidiDetail> pupukSubsidiDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "pupukSubsidi")
    private List<AlokasiPupukSubsidi> alokasiPupukSubsidi;

    @JsonIgnore
    @OneToMany(mappedBy = "pupukSubsidi")
    private List<PupukSubsidiStatus> pupukSubsidiStatus;

    @Column(nullable = false)
    @NotNull(message = "farmer_id cannot be null")
    private Long petaniId;

    @Column(nullable = false)
    @NotNull(message = "stand_id cannot be null")
    private Long kiosId;

    @Column(nullable = false)
    @NotNull(message = "year cannot be null")
    private int tahun;

    @Column(nullable = false)
    @NotNull(message = "planting_time cannot be null")
    private int masaTanam;

    @Column(nullable = false)
    @NotNull(message = "commodity cannot be null")
    @NotEmpty(message = "commodity cannot be empty")
    private String komoditas;

    @Column(nullable = false)
    @NotNull(message = "subsector cannot be null")
    @NotEmpty(message = "subsector cannot be empty")
    private String subsektor;

    @Column(nullable = false)
    @NotNull(message = "isUsed cannot be null")
    private boolean isDigunakan;

    @Column(nullable = false)
    @NotNull(message = "area cannot be null")
    private int luasLahan;

    @Column(nullable = false)
    @NotNull(message = "rejection_message cannot be null")
    @NotEmpty(message = "rejection_message cannot be empty")
    private String pesan;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public Long getPupukSubsidiId() {
        return pupukSubsidiId;
    }

    public void setPupukSubsidiId(Long pupukSubsidiId) {
        this.pupukSubsidiId = pupukSubsidiId;
    }

    public List<PupukSubsidiDetail> getPupukSubsidiDetail() {
        return pupukSubsidiDetail;
    }

    public void setPupukSubsidiDetail(List<PupukSubsidiDetail> pupukSubsidiDetail) {
        this.pupukSubsidiDetail = pupukSubsidiDetail;
    }

    public List<AlokasiPupukSubsidi> getAlokasiPupukSubsidi() {
        return alokasiPupukSubsidi;
    }

    public void setAlokasiPupukSubsidi(List<AlokasiPupukSubsidi> alokasiPupukSubsidi) {
        this.alokasiPupukSubsidi = alokasiPupukSubsidi;
    }

    public List<PupukSubsidiStatus> getPupukSubsidiStatus() {
        return pupukSubsidiStatus;
    }

    public void setPupukSubsidiStatus(List<PupukSubsidiStatus> pupukSubsidiStatus) {
        this.pupukSubsidiStatus = pupukSubsidiStatus;
    }

    public Long getPetaniId() {
        return petaniId;
    }

    public void setPetaniId(Long petaniId) {
        this.petaniId = petaniId;
    }

    public Long getKiosId() {
        return kiosId;
    }

    public void setKiosId(Long kiosId) {
        this.kiosId = kiosId;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(int masaTanam) {
        this.masaTanam = masaTanam;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getSubsektor() {
        return subsektor;
    }

    public void setSubsektor(String subsektor) {
        this.subsektor = subsektor;
    }

    public boolean isDigunakan() {
        return isDigunakan;
    }

    public void setDigunakan(boolean digunakan) {
        isDigunakan = digunakan;
    }

    public int getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(int luasLahan) {
        this.luasLahan = luasLahan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
