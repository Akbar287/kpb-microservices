package com.kpb.marketservice.domain;

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
@Table(name = "buyers")
public class Buyer implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252557698L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyer_id;

    @OneToMany(mappedBy = "buyer")
    private List<Order> orderList;

    @OneToMany(mappedBy = "buyer")
    private List<BankAcccount> bankAcccountList;

    @Column(name = "address", nullable = false)
    @NotNull(message = "address cannot be null")
    @NotEmpty(message = "address cannot be empty")
    private String address;

    @Column(name = "isRegistration")
    private boolean isRegistration;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;

}
