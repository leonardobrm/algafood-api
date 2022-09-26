package com.algaworks.algafood.infrastructure.controllers;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.entities.City;
import com.algaworks.algafood.domain.usecases.city.ICityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/cities")
public class CityController {
    private final ICityService cityUseCases;

    @PostMapping
    public ResponseEntity post(@RequestBody CreateCityRequest request) {
        this.cityUseCases.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        List<City> cities = this.cityUseCases.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getById(@Validated @PathVariable Long id) {
        City cities = this.cityUseCases.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@Validated @PathVariable Long id, @RequestBody UpdateCityRequest request) {
        this.cityUseCases.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Validated @PathVariable Long id) {
        this.cityUseCases.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
