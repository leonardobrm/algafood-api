package com.algaworks.algafood.infrastructure.repository.kitchen;

import com.algaworks.algafood.domain.entities.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IKitchenRepository extends JpaRepository<Kitchen, Long> {
    Optional<Kitchen> findByName(final String name);
}
