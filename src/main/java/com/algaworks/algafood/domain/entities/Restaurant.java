package com.algaworks.algafood.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "shipping_rate")
    private BigDecimal shippingRate;
    @Column(name = "is_open")
    private boolean isOpen;
    @ManyToOne()
    private Kitchen kitchen;
    @Column
    private Date created_at;
    @Column
    private Date updated_at;

    public Restaurant(String name, BigDecimal shippingRate) {
        this.name = name;
        this.shippingRate = shippingRate;
    }

    @PrePersist
    private void prePersist() {
        if (this.created_at == null) this.created_at = new Date();
        if (this.updated_at == null) this.updated_at = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.updated_at = new Date();
    }

    public Restaurant(String name, BigDecimal shippingRate, Kitchen kitchen) {
        this.name = name;
        this.shippingRate = shippingRate;
        this.kitchen = kitchen;
    }
}
