package com.algaworks.algafood.api.v1.controllers;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.entities.City;
import com.algaworks.algafood.domain.entities.State;
import com.algaworks.algafood.domain.service.city.ICityService;
import com.algaworks.algafood.domain.service.state.IStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cities")
public class CityController {
    private final ICityService cityService;

    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateCityRequest request){
        this.cityService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<City>>findAll(){
        List<City> cities = this.cityService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City>findById(@Validated @PathVariable Long id){
        City cities = this.cityService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Validated @PathVariable Long id, @RequestBody UpdateCityRequest request){
        this.cityService.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Validated @PathVariable Long id){
        this.cityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
