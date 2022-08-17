package com.algaworks.algafood.domain.service.city;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.entities.City;

import java.util.List;

public interface ICityService {

    void create(CreateCityRequest request);
    List<City> findAll();
    City findById(Long id);
    void update(Long id, UpdateCityRequest request);
    void delete(Long id);
}
