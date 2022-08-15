package com.algaworks.algafood.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Kitchen(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Kitchen kitchen = (Kitchen) o;
        return id != null && Objects.equals(id, kitchen.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void updatedName(String name){
        this.name = name;
    }
}
