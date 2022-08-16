package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.domain.dto.request.CreateRestaurantRequest;
import com.algaworks.algafood.domain.dto.request.UpdateRestaurantRequest;
import com.algaworks.algafood.domain.entities.Restaurant;
import com.algaworks.algafood.domain.service.restaurant.IRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

    private final IRestaurantService restaurantUseCase;

    public RestaurantController(IRestaurantService restaurantUseCase) {
        this.restaurantUseCase = restaurantUseCase;
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@Validated @RequestBody CreateRestaurantRequest request) {
        restaurantUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> delete(@Validated @PathVariable long id) {
        restaurantUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable long id, @Validated @RequestBody UpdateRestaurantRequest request) {
        restaurantUseCase.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@Validated @PathVariable long id) {
        Restaurant restaurant = restaurantUseCase.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> restaurants = restaurantUseCase.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }
}
