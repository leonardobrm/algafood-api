package com.algaworks.algafood.domain.repository.city;

import com.algaworks.algafood.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICityRepository extends JpaRepository<City, Long> {
    @Query("from City r where r.state.id = :id")
    Optional<List<City>> findByStateId(@Param("id") long id);

    Optional<City> findByName(String name);
}
