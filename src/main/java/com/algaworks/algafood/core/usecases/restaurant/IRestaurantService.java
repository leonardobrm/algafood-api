package com.algaworks.algafood.core.usecases.restaurant;

import com.algaworks.algafood.core.dto.request.restaurant.CreateRestaurantRequest;
import com.algaworks.algafood.core.dto.request.restaurant.UpdateRestaurantRequest;
import com.algaworks.algafood.core.model.Restaurant;

import java.util.List;

public interface IRestaurantService {
    void create(final CreateRestaurantRequest request);

    void delete(final long id);

    void update(final long id, UpdateRestaurantRequest request);

    List<Restaurant> findAll();

    Restaurant findById(final long id);

    List<Restaurant> findAllWithFreeShipping();
}
