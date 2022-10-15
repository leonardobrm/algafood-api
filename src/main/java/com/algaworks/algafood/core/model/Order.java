package com.algaworks.algafood.core.model;

import com.algaworks.algafood.core.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "\"order\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subTotal;
    private BigDecimal shippingRate;
    private BigDecimal totalValue;

    @Embedded
    private Address address;

    @Enumerated
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(nullable = false)
    private Date created_at;

    private Date confirmation_at;
    private Date cacelation_at;
    private Date delivery_at;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormOfPayment formOfPayment;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User client;

    @OneToMany()
    private List<ItemOrdered> items = new ArrayList<>();
}
