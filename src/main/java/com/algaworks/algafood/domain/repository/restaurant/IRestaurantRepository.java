package com.algaworks.algafood.domain.repository.restaurant;

import com.algaworks.algafood.domain.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);

    @Query("from Restaurant r where r.kitchen.id = :id")
    Optional<List<Restaurant>> findByKitchenId(@Param("id") long id);
}
