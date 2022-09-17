package com.algaworks.algafood.infrastructure.controllers;

import com.algaworks.algafood.domain.dto.request.restaurant.CreateRestaurantRequest;
import com.algaworks.algafood.domain.dto.request.restaurant.UpdateRestaurantRequest;
import com.algaworks.algafood.domain.entities.Restaurant;
import com.algaworks.algafood.domain.service.restaurant.IRestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {
    private final IRestaurantService restaurantService;


    @PostMapping
    public ResponseEntity post(@Validated @RequestBody CreateRestaurantRequest request) {
        restaurantService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Validated @PathVariable long id) {
        restaurantService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable long id, @Validated @RequestBody UpdateRestaurantRequest request) {
        restaurantService.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getById(@Validated @PathVariable long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> restaurants = restaurantService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/free-shippings")
    public ResponseEntity<List<Restaurant>> test() {
        List<Restaurant> restaurants = this.restaurantService.findAllWithFreeShipping();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }
}
