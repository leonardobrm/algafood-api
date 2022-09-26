package com.algaworks.algafood.domain.usecases.city;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.entities.City;

import java.util.List;

public interface ICityService {

    void create(final CreateCityRequest request);
    List<City> findAll();
    City findById(final Long id);
    void update(final Long id, UpdateCityRequest request);
    void delete(final Long id);
}
