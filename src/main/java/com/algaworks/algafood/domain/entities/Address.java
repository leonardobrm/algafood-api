package com.algaworks.algafood.domain.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String cep;
    @Column
    private String publicPlace;
    @Column
    private String number;
    @Column
    private String complement;
    @Column
    private String district;

    //@ManyToOne
    //private City city;
}
