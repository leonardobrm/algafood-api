package com.algaworks.algafood.domain.repository.state;

import com.algaworks.algafood.domain.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStateRepository extends JpaRepository<State, Long> {
    Optional<State> findByName(String name);
}
