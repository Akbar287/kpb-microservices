package com.kpb.transactionalservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "produk")
public class Produk implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283873325752573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produkId;

    @JsonIgnore
    @OneToOne(mappedBy = "produk")
    private Harga harga;

    @JsonIgnore
    @OneToMany(mappedBy = "produk")
    private List<TransaksiDetail> transaksiDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "produk")
    private List<Stok> stok;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "produk_kategori", joinColumns = @JoinColumn(name = "produk_id"), inverseJoinColumns = @JoinColumn(name = "kategori_id"))
    private Kategori kategori;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "produk_jenis", joinColumns = @JoinColumn(name = "produk_id"), inverseJoinColumns = @JoinColumn(name = "jenis_id"))
    private Jenis jenis;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "produk_distributor_relation", joinColumns = @JoinColumn(name = "produk_id"), inverseJoinColumns = @JoinColumn(name = "produk_distributor_id"))
    private List<ProdukDistributor> produkDistributor;

    @Column(nullable = false)
    @NotNull(message = "product_name date cannot be null")
    @NotEmpty(message = "product_name date cannot be empty")
    private String namaProduk;

    @Column(nullable = false)
    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be empty")
    private String satuan;

    @Column(nullable = false)
    @NotNull(message = "file_image cannot be null")
    @NotEmpty(message = "file_image cannot be empty")
    private String fileGambar;

    private String deskripsi;
    private boolean isPenebusan;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;
}
