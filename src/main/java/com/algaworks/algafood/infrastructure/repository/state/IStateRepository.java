package com.algaworks.algafood.infrastructure.repository.state;

import com.algaworks.algafood.core.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStateRepository extends JpaRepository<State, Long> {
    Optional<State> findByName(final String name);
}
