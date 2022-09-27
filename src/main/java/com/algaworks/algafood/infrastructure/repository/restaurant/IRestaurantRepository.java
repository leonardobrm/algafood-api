package com.algaworks.algafood.infrastructure.repository.restaurant;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    Optional<Restaurant> findByName(final String name);

    @Query("from Restaurant r where r.kitchen.id = :id")
    Optional<List<Restaurant>> findByKitchenId(final @Param("id") long id);
}
