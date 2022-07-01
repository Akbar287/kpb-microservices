package com.kpb.transactionalservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "produk_distributor")
public class ProdukDistributor implements Serializable {

    @Serial
    private static final long serialVersionUID = -6283873325662573809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long distributorProdukId;

    @Column(nullable = false)
    @NotNull(message = "distributor cannot be null")
    private Long distributorId;

    @ManyToMany(mappedBy = "produkDistributor")
    private List<Produk> produk;
}
