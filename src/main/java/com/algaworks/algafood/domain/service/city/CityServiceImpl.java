package com.algaworks.algafood.domain.service.city;

import com.algaworks.algafood.domain.dto.request.city.CreateCityRequest;
import com.algaworks.algafood.domain.dto.request.city.UpdateCityRequest;
import com.algaworks.algafood.domain.entities.City;
import com.algaworks.algafood.domain.entities.State;
import com.algaworks.algafood.domain.exception.errors.ApiException;
import com.algaworks.algafood.domain.repository.city.ICityRepository;
import com.algaworks.algafood.domain.repository.state.IStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityServiceImpl implements ICityService{

    private final ICityRepository cityRepository;
    private final IStateRepository stateRepository;

    public CityServiceImpl(ICityRepository cityRepository, IStateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public void create(CreateCityRequest request) {
        Optional<City> verifyCityExists = this.cityRepository.findByName(request.getName());
        if(verifyCityExists.isPresent())  throw new ApiException("City already exists", HttpStatus.BAD_REQUEST);

        State state = this.stateRepository.findById(request.getIdState()).orElseThrow(() -> {
            throw new ApiException("state not found", HttpStatus.BAD_REQUEST);
        });

        City newCity = new City(request.getName(), state);
        this.cityRepository.save(newCity);
        log.info("City created successfully");
    }

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("city not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public void update(Long id, UpdateCityRequest request) {
        City verifyCityExists = this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("city not found", HttpStatus.NOT_FOUND);
        });
        State state = null;
        if(request.getIdState() != null){
            state = this.stateRepository.findById(id).orElseThrow(() -> {
                throw new ApiException("State not found", HttpStatus.NOT_FOUND);
            });
        }
        String validatedName = request.getName() != null && request.getName() != verifyCityExists.getName() ? request.getName()
                : verifyCityExists.getName();

        State validateState = state != null && request.getIdState() != verifyCityExists.getState().getId() ? state
                : verifyCityExists.getState();

        City newCity = new City(validatedName, validateState);
        BeanUtils.copyProperties(newCity,  verifyCityExists, "id");

        this.cityRepository.save(verifyCityExists);
        log.info("City updated successfully");
    }

    @Override
    public void delete(Long id) {
        City findCityExists = this.cityRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("city not found", HttpStatus.NOT_FOUND);
        });

        this.cityRepository.delete(findCityExists);
        log.info("City deleted successfully");
    }
}
