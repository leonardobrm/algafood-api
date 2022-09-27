package com.algaworks.algafood.domain.usecases.city;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.infrastructure.exception.errors.ApiException;
import com.algaworks.algafood.infrastructure.repository.city.ICityRepository;
import com.algaworks.algafood.infrastructure.repository.state.IStateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CityServiceImpl implements ICityService {

    private final String CITY_NOT_EXISTS = "city not found";

    private final ICityRepository cityRepository;
    private final IStateRepository stateRepository;

    @Override
    public void create(final CreateCityRequest request) {
        var verifyCityExists = this.cityRepository.findByName(request.name());
        if (verifyCityExists.isPresent()) throw new ApiException("City already exists", HttpStatus.BAD_REQUEST);

        var state = this.stateRepository.findById(request.idState()).orElseThrow(() -> {
            throw new ApiException("state not found", HttpStatus.BAD_REQUEST);
        });
        var newCity = new City(request.name(), state);
        this.cityRepository.save(newCity);
        log.debug("City created successfully");
    }

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City findById(final Long id) {
        return this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException(CITY_NOT_EXISTS, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public void update(final Long id, final UpdateCityRequest request) {
        var verifyCityExists = this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException(CITY_NOT_EXISTS, HttpStatus.NOT_FOUND);
        });
        State state = null;
        if (request.idState() != null) {
            state = this.stateRepository.findById(id).orElseThrow(() -> {
                throw new ApiException("State not found", HttpStatus.NOT_FOUND);
            });
        }
        String validatedName = request.name() != null && !request.name().equals(verifyCityExists.getName()) ? request.name()
                : verifyCityExists.getName();

        State validateState = state != null && !request.idState().equals(verifyCityExists.getState().getId()) ? state
                : verifyCityExists.getState();

        var newCity = new City(validatedName, validateState);
        BeanUtils.copyProperties(newCity, verifyCityExists, "id");

        this.cityRepository.save(verifyCityExists);
        log.info("City updated successfully");
    }

    @Override
    public void delete(final Long id) {
        var findCityExists = this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException(CITY_NOT_EXISTS, HttpStatus.NOT_FOUND);
        });

        this.cityRepository.delete(findCityExists);
        log.info("City deleted successfully");
    }
}
