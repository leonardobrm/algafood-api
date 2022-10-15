package com.algaworks.algafood.core.usecases.city;

import com.algaworks.algafood.core.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.core.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.core.model.City;

import java.util.List;

public interface ICityService {

    void create(final CreateCityRequest request);
    List<City> findAll();
    City findById(final Long id);
    void update(final Long id, UpdateCityRequest request);
    void delete(final Long id);
}
