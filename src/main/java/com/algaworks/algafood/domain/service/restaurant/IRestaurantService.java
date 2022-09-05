package com.algaworks.algafood.domain.service.restaurant;

import com.algaworks.algafood.domain.dto.request.restaurant.CreateRestaurantRequest;
import com.algaworks.algafood.domain.dto.request.restaurant.UpdateRestaurantRequest;
import com.algaworks.algafood.domain.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    void create(CreateRestaurantRequest request);

    void delete(long id);

    void update(long id, UpdateRestaurantRequest request);

    List<Restaurant> findAll();

    Restaurant findById(long id);
    List<Restaurant> findAllWithFreeShipping();
}
