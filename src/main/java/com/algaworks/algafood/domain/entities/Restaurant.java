package com.algaworks.algafood.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    private Kitchen kitchen;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_form_payment",
    joinColumns = @JoinColumn(name = "restaurant_id"),
    inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
    private List<FormOfPayment> formOfPayments;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> product = new ArrayList<>();

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

    public Restaurant(String name, BigDecimal shippingRate, Kitchen kitchen) {
        this.name = name;
        this.shippingRate = shippingRate;
        this.kitchen = kitchen;
    }
}
