package com.algaworks.algafood.domain.useCases.restaurant;

import com.algaworks.algafood.domain.entities.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface IRestaurantUseCase {
    void create(String name, BigDecimal shippingRate, String kitchenName);
    void delete(long id);
    void update(long id, String name, BigDecimal shippingRate);
    List<Restaurant> getAll();
    Restaurant get(long id);
}
