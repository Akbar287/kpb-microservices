package com.kpb.marketservice.domain;

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
@Table(name = "orders")
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = -6283155326252556549L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @OneToMany(mappedBy = "order")
    private List<OrderStatus> orderStatusList;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Column(name = "grand_total", nullable = false)
    @NotNull(message = "grand_total cannot be null")
    @NotEmpty(message = "grand_total cannot be empty")
    private BigInteger grand_total;

    @Column(name = "fee_shipping", nullable = false)
    @NotNull(message = "fee_shipping cannot be null")
    @NotEmpty(message = "fee_shipping cannot be empty")
    private BigInteger fee_shipping;

    @Column(name = "order_address", nullable = false)
    @NotNull(message = "order_address cannot be null")
    @NotEmpty(message = "order_address cannot be empty")
    private String order_address;

    @Column(name = "payment_method", nullable = false)
    @NotNull(message = "payment_method cannot be null")
    @NotEmpty(message = "payment_method cannot be empty")
    private String payment_method;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
