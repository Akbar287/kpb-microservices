package com.kpb.marketservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = -6784255326252559809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_detail_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name = "price", nullable = false)
    @NotNull(message = "price cannot be null")
    @NotEmpty(message = "price cannot be empty")
    private BigInteger price;

    @Column(name = "quantity", nullable = false)
    @NotNull(message = "quantity cannot be null")
    @NotEmpty(message = "quantity cannot be empty")
    private int quantity;

    @Column(name = "total_price", nullable = false)
    @NotNull(message = "total_price cannot be null")
    @NotEmpty(message = "total_price cannot be empty")
    private BigInteger total_price;

    @Column(name = "unit", nullable = false)
    @NotNull(message = "unit cannot be null")
    @NotEmpty(message = "unit cannot be empty")
    private String unit;

    @Column(name = "discount", nullable = false)
    @NotNull(message = "discount cannot be null")
    @NotEmpty(message = "discount cannot be empty")
    private BigInteger discount;
}
