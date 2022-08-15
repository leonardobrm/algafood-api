package com.algaworks.algafood.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
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

    @ManyToOne
    private Kitchen kitchen;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @PrePersist
    private void prePersist() {
        if (this.created_at == null) this.created_at = new Date();
        if (this.updated_at == null) this.updated_at = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.updated_at = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Restaurant(String name, BigDecimal shippingRate, Kitchen kitchen) {
        this.name = name;
        this.shippingRate = shippingRate;
        this.kitchen = kitchen;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateShippingRate(BigDecimal shippingRate) {
        this.shippingRate = shippingRate;
    }
}
