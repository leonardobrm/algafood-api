package com.algaworks.algafood.domain.service.restaurant;

import com.algaworks.algafood.domain.dto.request.CreateRestaurantRequest;
import com.algaworks.algafood.domain.dto.request.UpdateRestaurantRequest;
import com.algaworks.algafood.domain.entities.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface IRestaurantService {
    void create(CreateRestaurantRequest request);
    void delete(long id);
    void update(long id, UpdateRestaurantRequest request);
    List<Restaurant> getAll();
    Restaurant get(long id);
}
