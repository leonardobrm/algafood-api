package com.algaworks.algafood.domain.controllers;

import com.algaworks.algafood.domain.entities.Restaurant;
import com.algaworks.algafood.domain.useCases.restaurant.IRestaurantUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurant")
public class RestaurantController {

    private final IRestaurantUseCase restaurantUseCase;

    public RestaurantController(IRestaurantUseCase restaurantUseCase) {
        this.restaurantUseCase = restaurantUseCase;
    }

    @PostMapping
    public void create(@RequestParam String name, @RequestParam BigDecimal shippingRate, @RequestParam String kitchenName){
        restaurantUseCase.create(name, shippingRate, kitchenName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        restaurantUseCase.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestParam String name, @RequestParam BigDecimal shippingRate){
        restaurantUseCase.update(id, name, shippingRate);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable long id){
        return restaurantUseCase.get(id);
    }

    @GetMapping()
    public List<Restaurant> getAll(){
        return restaurantUseCase.getAll();
    }
}
